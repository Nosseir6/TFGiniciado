package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * La clase Puerta representa un objeto de puerta en el juego.
 * Extiende la clase SuperObjeto.
 */
public class Puerta extends SuperObjeto {

    /** Indica si la puerta está abierta. */
    public boolean abierta;

    /** La instancia del panel de juego al que pertenece la puerta. */
    PanelJuego pJuego;

    /**
     * Constructor para la clase Puerta.
     * @param pJuego La instancia del panel de juego al que pertenece la puerta.
     */
    public Puerta(PanelJuego pJuego) {
        // Llama al constructor de la clase SuperObjeto
        super();

        // Guarda la instancia del panel de juego
        this.pJuego = pJuego;

        // Establece el nombre del objeto como PUERTA
        nombre = TipoObjetos.PUERTA;

        try {
            // Lee la imagen de la puerta desde el archivo de recursos
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/puerta.png"));
        } catch (IOException e) {
            // Manejo de errores en caso de que la imagen no se pueda cargar
            e.printStackTrace();
            System.out.println("No se ha encontrado la imagen de la puerta");
        }

        // Indica que la puerta tiene colisión
        colision = true;

        // Inicialmente la puerta está cerrada
        abierta = false;
    }

}
