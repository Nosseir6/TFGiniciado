package casilla;

import main.PanelJuego;
import main.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * La clase GestorCasillas se encarga de gestionar las casillas del juego, incluyendo
 * la carga de imágenes, la configuración de las casillas y el dibujo del mapa de juego.
 */
public class GestorCasillas {
    PanelJuego pJuego;
    public Casilla[] casillas;
    public int[][] mapaJuego;

    /**
     * Constructor para la clase GestorCasillas.
     * @param panelJuego La referencia al objeto PanelJuego que contiene la configuración del juego.
     */
    public GestorCasillas(PanelJuego panelJuego) {
        this.pJuego = panelJuego;

        casillas = new Casilla[15];
        mapaJuego = new int[pJuego.maxJuegoColumna][pJuego.maxJuegoFila];

        getCasillaImage();
        cargarMapa();
    }

    /**
     * Método que carga las imágenes de las casillas y las configura.
     */
    public void getCasillaImage() {
        // CASILLAS PARA EL SUELO
        setup(0, "/casillas/suelo/suelo0.png", false);
        setup(1, "/casillas/suelo/suelo1.png", false);
        setup(2, "/casillas/suelo/suelo2.png", false);
        setup(3, "/casillas/suelo/suelo3.png", false);

        // CASILLAS PARA LAS PAREDES
        setup(4, "/casillas/paredes/muro1.png", true);
        setup(5, "/casillas/paredes/muro2.png", true);
        setup(6, "/casillas/paredes/muro3.png", true);
        setup(7, "/casillas/paredes/muro4.png", true);
        setup(8, "/casillas/paredes/muro5.png", true);
    }

    /**
     * Método que configura una casilla específica.
     * @param i El índice de la casilla en el arreglo.
     * @param rutaImagen La ruta de la imagen de la casilla.
     * @param colision Indica si la casilla tiene colisión o no.
     */
    public void setup(int i, String rutaImagen, boolean colision) {
        Util util = new Util();
        try {
            casillas[i] = new Casilla();
            casillas[i].imagen = ImageIO.read(getClass().getResourceAsStream(rutaImagen));
            casillas[i].imagen = util.escalada(casillas[i].imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);
            casillas[i].colision = colision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que dibuja las casillas del mapa en la pantalla.
     * @param g2 El objeto Graphics2D utilizado para dibujar.
     */
    public void draw(Graphics2D g2) {
        int colMundo = 0;
        int filaMundo = 0;

        while (colMundo < pJuego.maxJuegoColumna && filaMundo < pJuego.maxJuegoFila) {
            int numCasilla = mapaJuego[colMundo][filaMundo];

            int xMundo = colMundo * pJuego.dimensionCasillas; // Posición en el mundo
            int yMundo = filaMundo * pJuego.dimensionCasillas; // Posición en el mundo
            int xPantalla = xMundo - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla;
            int yPantalla = yMundo - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;

            // Dibujar casilla solo si está en la pantalla del jugador
            if ((xMundo + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                    (xMundo - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                    (yMundo + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                    (yMundo - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla)) {
                g2.drawImage(casillas[numCasilla].imagen, xPantalla, yPantalla, null);
            }
            colMundo++;

            if (colMundo == pJuego.maxCol) {
                colMundo = 0;
                filaMundo++;
            }
        }
    }

    /**
     * Método que carga el mapa de juego desde un archivo de texto.
     */
    public void cargarMapa() {
        try {
            FileReader fr = new FileReader("mapas/MapaAvanzado.txt");
            BufferedReader br = new BufferedReader(fr);

            for (int fila = 0; fila < pJuego.maxJuegoFila; fila++) {
                String linea = br.readLine();
                if (linea == null) break; // Comprobar fin de archivo
                String[] codigo = linea.split(" ");
                for (int col = 0; col < pJuego.maxJuegoColumna; col++) {
                    int codigoCasilla = Integer.parseInt(codigo[col]);
                    mapaJuego[col][fila] = codigoCasilla;
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
