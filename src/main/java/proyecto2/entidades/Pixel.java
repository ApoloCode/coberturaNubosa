package proyecto2.entidades;

/**
 *
 * @author axel
 * @author carlos
 */
public class Pixel {

    private int color;
    private int x;
    private int y;
    private boolean nube;

    /**
     * Constructor que recibe el color y las coordenas x y y al que hace
     * referencia esta instancia.
     *
     * @param color
     * @param x
     * @param y
     */
    public Pixel(int color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter del color
     *
     * @return
     */
    public int getColor() {
        return color;
    }

    /**
     * Setter de color
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Regresa el booleano de nube
     *
     * @return
     */
    public boolean isNube() {
        return nube;
    }

    /**
     * Setter de nube
     *
     * @param nube
     */
    public void setNube(boolean nube) {
        this.nube = nube;
    }

    /**
     * Getter de la coordenada x
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Setter de la coordenada x
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter de la coordenada Y
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Setter de la coordenada Y
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
