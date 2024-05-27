package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * La clase Cofre representa un objeto que puede contener elementos especiales o tesoros en el juego.
 * Extiende la clase SuperObjeto.
 */
public class Cofre extends SuperObjeto {

    /** Indica si el cofre está abierto. */
    public boolean abierto;

    /** La instancia del panel de juego al que pertenece el cofre. */
    PanelJuego pJuego;

    /**
     * Constructor para la clase Cofre.
     * @param pJuego La instancia del panel de juego al que pertenece el cofre.
     */
    public Cofre(PanelJuego pJuego) {
        // Llama al constructor de la clase SuperObjeto
        super();

        // Establece el nombre del objeto como COFRE
        nombre = TipoObjetos.COFRE;

        try {
            // Lee la imagen del cofre desde el archivo de recursos
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/cofreNuevo.png"));

            // Escala la imagen al tamaño de una casilla en el juego
            util.escalada(imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Marca el objeto como colisionable
        colision = true;

        // Inicialmente, el cofre está cerrado
        abierto = false;
    }
}
