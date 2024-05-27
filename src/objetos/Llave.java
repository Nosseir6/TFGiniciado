package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * La clase Llave representa un objeto de llave que puede ser recolectado por el jugador en el juego.
 * Extiende la clase SuperObjeto.
 */
public class Llave extends SuperObjeto {

    /** La instancia del panel de juego al que pertenece la llave. */
    PanelJuego pJuego;

    /**
     * Constructor para la clase Llave.
     * @param pJuego La instancia del panel de juego al que pertenece la llave.
     */
    public Llave(PanelJuego pJuego) {
        // Llama al constructor de la clase SuperObjeto
        super();

        // Guarda la instancia del panel de juego
        this.pJuego = pJuego;

        // Establece el nombre del objeto como LLAVE
        nombre = TipoObjetos.LLAVE;

        try {
            // Lee la imagen de la llave desde el archivo de recursos
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/llave.png"));
            // Escala la imagen al tamaño de una casilla en el juego
            util.escalada(imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
