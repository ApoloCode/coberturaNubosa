package proyecto2.servicios;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import proyecto2.entidades.Imagen;
import proyecto2.entidades.Pixel;

/**
 *
 * @author axel
 * @author carlos
 */
public abstract class ServiciosImagen {

    /**
     * Recibe una imagen. Devuelve un arreglo de tamaño dos con las dimensiones
     * de la imagen, la longitud en la primer entrada, y la altura en la
     * segunda.
     *
     * @param imagen
     * @return
     */
    public static int[] getDimensiones(Imagen imagen) {
        BufferedImage bi = imagen.getImage();
        int[] dimensiones = new int[2];
        dimensiones[0] = bi.getWidth();
        dimensiones[1] = bi.getHeight();
        return dimensiones;
    }

    /**
     * Recibe una imagen. Devuelve una instancia de Pixel que representa el
     * pixel central de la imagen.
     *
     * @param imagen
     * @return
     */
    public static Pixel getCentro(Imagen imagen) {
        BufferedImage bi = imagen.getImage();
        int[] dimensiones = getDimensiones(imagen);
        int[] coordCentro = {dimensiones[0] / 2, dimensiones[1] / 2};
        int colorCentro = bi.getRGB(coordCentro[0], coordCentro[1]);
        Pixel centro = new Pixel(colorCentro, coordCentro[0], coordCentro[1]);
        return centro;
    }

    /**
     * Recibe una imagen. Devuelve una matriz de instancias de Pixel de ancho y
     * largo los de la imagen. En esta matriz se guardan los datos de color y
     * coordenadas de cada pizel de la imagen-
     *
     * @param imagen
     * @return
     */
    public static Pixel[][] pixelize(Imagen imagen) {
        int[] dimensiones = getDimensiones(imagen);
        BufferedImage bi = imagen.getImage();
        Pixel[][] pixeles = new Pixel[dimensiones[0]][dimensiones[1]];

        for (int x = 0; x < dimensiones[0]; x++) {
            for (int y = 0; y < dimensiones[1]; y++) {
                int color = bi.getRGB(x, y);
                pixeles[x][y] = new Pixel(color, x, y);
            }
        }

        return pixeles;
    }

    /**
     * Recibe una imagen y un destino. Crea una copia de esta imagen en el
     * directorio destino. Devuelve una instancia de Imagen representando esta
     * copia.
     *
     * @param imagen
     * @param destino
     * @return
     * @throws IOException
     */
    public static Imagen copy(Imagen imagen, String destino) throws IOException {
        File in = imagen.getArchivo();
        File out = new File(destino);
        Files.copy(in.toPath(), out.toPath());

        /**
         * Ya debería haber una copia de imagen en la dirección de destino.
         */
        Imagen copia = new Imagen(destino);
        return copia;
    }

    /**
     * Recibe una imagen y un pixel. Cambia el color del pixel en la imagen con
     * las coordenadas del pixel al color del pixel.
     *
     * @param imagen
     * @param pixel
     */
    public static void write(Imagen imagen, Pixel pixel) {
        BufferedImage bi = imagen.getImage();
        bi.setRGB(pixel.getX(), pixel.getY(), pixel.getColor());
    }

    /**
     * Recibe una imagen. Borra el archivo al que hace referencia.
     *
     * @param imagen
     */
    public static void delete(Imagen imagen) {
        File archivoImagen = imagen.getArchivo();
        archivoImagen.delete();
    }

    /**
     * Recibe una imagen. Calcula el Índice de Cobertura Nubosa (CCI) de la
     * imagen. Se entiende que la imagen de entrada es una del cielo de día.
     *
     * @param imagen
     * @return
     */
    public static double calcularCCI(Imagen imagen) {

        Pixel[][] pixeles = pixelize(imagen);
        int[] dimensiones = getDimensiones(imagen);
        Pixel centro = getCentro(imagen);
        ArrayList<Pixel> nubes = new ArrayList<>();
        ArrayList<Pixel> cielo = new ArrayList<>();

        for (int x = 0; x < dimensiones[0]; x++) {
            for (int y = 0; y < dimensiones[1]; y++) {
                Pixel pixel = pixeles[x][y];
                int cambioX = pixel.getX() - centro.getX();
                int cambioY = pixel.getY() - centro.getY();
                if ((double) Math.sqrt((cambioX * cambioX) + (cambioY * cambioY)) < 1330.00) {
                    ServiciosPixel.clasify(pixel);
                    if (pixel.isNube()) {
                        nubes.add(pixel);
                    } else {
                        cielo.add(pixel);
                    }
                }
            }
        }

        return (double) nubes.size() / (double) (nubes.size() + cielo.size());
    }

}
