package casilla;

import java.awt.image.BufferedImage;

/**
 * La clase Casilla representa una casilla del juego.
 * Cada casilla puede tener una imagen asociada y puede indicar si tiene una colisión.
 */
public class Casilla
{
    /**
     * La imagen de la casilla. Puede ser utilizada para representar la apariencia gráfica de la casilla.
     */
    public BufferedImage imagen;

    /**
     * Indica si la casilla tiene colisión. Si es true, la casilla es colisionable, de lo contrario no lo es.
     */
    public boolean colision;
}
