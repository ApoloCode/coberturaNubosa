package proyecto2.entidades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author axel
 * @author carlos
 */
public class Imagen {

    private BufferedImage image;
    private File archivo;

    /**
     *
     * Constructor vacío.
     */
    public Imagen() {
        this.image = null;
        this.archivo = null;
    }

    /**
     *
     * Constructor que recibe la dirección del archvivo al que hace referencia
     * esta instancia.
     *
     * @param dirección
     * @throws IOException
     */
    public Imagen(String dirección) throws IOException {
        this.archivo = new File(dirección);
        this.image = ImageIO.read(archivo);
    }

    /**
     *
     * Getter de archivo.
     *
     * @return
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     *
     * Setter de archivo.
     *
     * @param dirección
     */
    public void setArchivo(String dirección) {
        this.archivo = new File(dirección);
    }

    /**
     *
     * Getter de image.
     *
     * @return
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     *
     * Setter de Image
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
