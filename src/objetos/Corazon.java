package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * La clase Corazon representa un objeto que puede restaurar la vida del jugador en el juego.
 * Extiende la clase SuperObjeto.
 */
public class Corazon extends SuperObjeto {

    /** La instancia del panel de juego al que pertenece el corazón. */
    PanelJuego pJuego;

    /**
     * Constructor para la clase Corazon.
     * @param pJuego La instancia del panel de juego al que pertenece el corazón.
     */
    public Corazon(PanelJuego pJuego) {
        // Llama al constructor de la clase SuperObjeto
        super();

        // Guarda la instancia del panel de juego
        this.pJuego = pJuego;

        // Establece el nombre del objeto como CORAZON
        nombre = TipoObjetos.CORAZON;

        try {
            // Lee la imagen del corazón completo desde el archivo de recursos
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/corazon/corazonComp.png"));
            // Escala la imagen al tamaño de una casilla en el juego
            util.escalada(imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);

            // Lee la imagen del medio corazón desde el archivo de recursos
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/objetos/corazon/medioCor.png"));
            // Escala la imagen al tamaño de una casilla en el juego
            util.escalada(imagen2, pJuego.dimensionCasillas, pJuego.dimensionCasillas);

            // Lee la imagen del corazón vacío desde el archivo de recursos
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/objetos/corazon/corazonVacio.png"));
            // Escala la imagen al tamaño de una casilla en el juego
            util.escalada(imagen3, pJuego.dimensionCasillas, pJuego.dimensionCasillas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
