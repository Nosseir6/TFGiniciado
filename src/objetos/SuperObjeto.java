package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;
import main.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase SuperObjeto representa un objeto genérico en el juego.
 * Contiene atributos y métodos comunes para todos los objetos del juego.
 */
public class SuperObjeto {

    /** La imagen del objeto. */
    public BufferedImage imagen, imagen2, imagen3;

    /** El tipo de objeto. */
    public TipoObjetos nombre;

    /** Indica si el objeto tiene colisión. */
    public boolean colision = false;

    /** La posición en el mundo del objeto en coordenadas X e Y. */
    public int posMundoX, posMundoY;

    /** El área de colisión del objeto representada como un rectángulo. */
    public Rectangle hitbox = new Rectangle(0, 0, 64, 64);

    /** La posición por defecto del hitbox en coordenadas X e Y. */
    public int hitBoxDefaultX = 0;
    public int hitBoxDefaultY = 0;

    /** Una instancia de la clase Util para utilizar métodos de utilidad. */
    Util util = new Util();

    /**
     * Método para dibujar el objeto en la pantalla de juego.
     * @param g2 El contexto gráfico en el que se dibuja el objeto.
     * @param pJuego La instancia del panel de juego al que pertenece el objeto.
     */
    public void draw(Graphics2D g2, PanelJuego pJuego) {
        int xPantalla = posMundoX - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla;
        int yPantalla = posMundoY - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;

        // Código para mejorar la eficiencia de dibujado del mapa, solo dibuja si está en la pantalla del jugador
        if ((posMundoX + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                (posMundoX - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                (posMundoY + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                (posMundoY - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla)) {
            g2.drawImage(imagen, xPantalla, yPantalla, pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);
        }
    }
}
