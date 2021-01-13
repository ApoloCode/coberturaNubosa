package proyecto2;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import proyecto2.entidades.Pixel;
import proyecto2.entidades.Imagen;
import proyecto2.servicios.ServiciosPixel;
import proyecto2.servicios.ServiciosImagen;

/**
 *
 * @author axel
 * @author carlos
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Obtenemos las direcciones de los archivos, tanto el solicitado como la copia.
        String separador = Pattern.quote(".");
        String[] partes = args[0].split(separador);
        String archivo = "src/resources/" + args[0];
        String destino = "src/resources/" + partes[0] + "-seg.jpg";

        //Este try se asegura de que el archivo solicitado exista.
        try {
            //Creamos la Imagen que hace referencia al archivo. Calculamos el CCI.
            Imagen imagen = new Imagen(archivo);
            double cci = ServiciosImagen.calcularCCI(imagen);

            //Si se solicitó la bandera -s, entra estos if
            if (args.length == 2) {
                if (args[1].equals("-s") || args[1].equals("-S")) {

                    //Si ya existe la imagen que se quiere generar, se borra el archivo.
                    File file = new File(destino);
                    if (file.exists()) {
                        file.delete();
                    }

                    //Se crea la copia de la imagen.
                    Imagen imagenCopia = ServiciosImagen.copy(imagen, destino);
                    int[] dimensiones = ServiciosImagen.getDimensiones(imagen);

                    //Se pizeliza la imagen y se obtiene su centro.
                    Pixel centro = ServiciosImagen.getCentro(imagen);
                    Pixel[][] pixeles = ServiciosImagen.pixelize(imagenCopia);

                    //Se clasifican los pixeles como cielo o nube. Solo se toman en cuenta los pixeles dentro del círculo, que es un prerequisito. Los pixeles fuera del círculo se colorean de negro indistintamente.
                    for (int x = 0; x < dimensiones[0]; x++) {
                        for (int y = 0; y < dimensiones[1]; y++) {
                            Pixel pixel = pixeles[x][y];
                            int cambioX = pixel.getX() - centro.getX();
                            int cambioY = pixel.getY() - centro.getY();
                            if ((double) Math.sqrt((cambioX * cambioX) + (cambioY * cambioY)) < 1324.00) {
                                ServiciosPixel.clasify(pixel);
                                if (pixel.isNube()) {
                                    pixel.setColor(-1);
                                } else {
                                    pixel.setColor(-16777216);
                                }
                                ServiciosImagen.write(imagenCopia, pixel);
                            } else {
                                pixel.setColor(-16777216);
                                ServiciosImagen.write(imagenCopia, pixel);
                            }
                        }

                    }

                    //Se escriben los cambios en la imagen copiada
                    ImageIO.write(imagenCopia.getImage(), "jpg", imagenCopia.getArchivo());

                } else {
                    //Caso en el que no se use una bandera válida.
                    System.out.println("No se reconoce la bandera " + args[1]);
                }
            }

            //Sacamos a salida estándar el CCI.
            System.out.println("Índice de Cobertura Nubosa de " + args[0] + ": " + cci);

        } catch (IOException ex) {
            //Exception que atrapamos en caso de que no existe el archivo solicitado.
            System.out.println("El archivo " + args[0] + " no pudo ser encontrado en la carpeta /src/resources.");
        }

    }
}
