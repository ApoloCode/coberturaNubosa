package proyecto2.servicios;

import java.awt.Color;
import proyecto2.entidades.Pixel;

/**
 *
 * @author axel
 * @author carlos
 */
public abstract class ServiciosPixel {

    /**
     * Pasa de un entero a un color
     *
     * @param pixel
     * @return
     */
    private static Color intToColor(Pixel pixel) {
        int argb = pixel.getColor();
        Color color = new Color(argb);
        return color;
    }

    /**
     * Getter del color rojo
     *
     * @param pixel
     * @return
     */
    private static int getRed(Pixel pixel) {
        Color color = intToColor(pixel);
        int red = color.getRed();
        return red;
    }

    /**
     * Getter del color verde
     *
     * @param pixel
     * @return
     */
    private static int getGreen(Pixel pixel) {
        Color color = intToColor(pixel);
        int green = color.getGreen();
        return green;
    }

    /**
     * Getter del color azul
     *
     * @param pixel
     * @return
     */
    private static int getBlue(Pixel pixel) {
        Color color = intToColor(pixel);
        int blue = color.getBlue();
        return blue;
    }

    /**
     * Getter del RGB
     *
     * @param pixel
     * @return
     */
    public static int[] getRGB(Pixel pixel) {
        int[] rgb = new int[3];
        rgb[0] = getRed(pixel);
        rgb[1] = getGreen(pixel);
        rgb[2] = getBlue(pixel);
        return rgb;
    }

    /**
     * Clasifica si el pixel recibido es Nube o no y le asigna el booleano
     * correspondiente
     *
     * @param pixel
     * @return
     */
    private static boolean esNube(Pixel pixel) {
        double red = (double) getRed(pixel);
        double blue = (double) getBlue(pixel);
        double ratio = red / blue;

        if (ratio > 0.95) {
            return true;
        }

        return false;
    }

    /**
     * Clasifica el pixel recibido
     *
     * @param pixel
     */
    public static void clasify(Pixel pixel) {
        pixel.setNube(esNube(pixel));
    }

    /**
     * Getter de las coordenas del pixel recibido
     *
     * @param pixel
     * @return
     */
    public static int[] getCoordinates(Pixel pixel) {
        int[] coordinates = new int[2];
        coordinates[0] = pixel.getX();
        coordinates[1] = pixel.getY();
        return coordinates;
    }
}
