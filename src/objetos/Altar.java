package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * La clase Altar representa un objeto especial en el juego que marca el final del nivel.
 * Extiende la clase SuperObjeto.
 */
public class Altar extends SuperObjeto {

    /** Indica si el altar está abierto. */
    public boolean abierto;

    /** La instancia del panel de juego al que pertenece el altar. */
    PanelJuego pJuego;

    /**
     * Constructor para la clase Altar.
     * @param pJuego La instancia del panel de juego al que pertenece el altar.
     */
    public Altar(PanelJuego pJuego) {
        // Llama al constructor de la clase SuperObjeto
        super();

        // Establece el nombre del objeto como ALTAR
        nombre = TipoObjetos.ALTAR;

        try {
            // Lee la imagen del altar desde el archivo de recursos
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/altarFinal.png"));

            // Escala la imagen al tamaño de una casilla en el juego
            util.escalada(imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Marca el objeto como colisionable
        colision = true;

        // Inicialmente, el altar está cerrado
        abierto = false;
    }
}
