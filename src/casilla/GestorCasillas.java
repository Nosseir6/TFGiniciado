package casilla;

import main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestorCasillas {
    PanelJuego pJuego;
    public Casilla[] casillas;
    public int[][] mapaJuego;


    public GestorCasillas(PanelJuego panelJuego) {
        this.pJuego = panelJuego;

        casillas = new Casilla[10];
        mapaJuego = new int[pJuego.maxJuegoColumna][pJuego.maxJuegoFila];

        getCasillaImage();
        cargarMapa();
    }

    public void getCasillaImage() {
        try {
            casillas[0] = new Casilla();
            casillas[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/marble_floor1.png"));

            casillas[1] = new Casilla();
            casillas[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/marble_wall1.png"));
            casillas[1].colision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int colMundo = 0;
        int filaMundo = 0;


        while (colMundo < pJuego.maxJuegoColumna && filaMundo < pJuego.maxJuegoFila) {
            int numCasilla = mapaJuego[colMundo][filaMundo];

            int xMundo = colMundo * pJuego.dimensionCasillas; //posicion en el mundo
            int yMundo = filaMundo * pJuego.dimensionCasillas; //posicion en el mundo
            int xPantalla = xMundo - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla; //parte de la pantalla en la que lo dibujamos, se suma la posicion de la pantalla para compensar la diferencia en caso de las esquinas
            int yPantalla = yMundo - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;

            //Codigo para mejorar la eficiencia de dibujado del mapa dinuja solo si esta en la pantalla del jugador
            //En lugar de dibujar "to_do el mapa
            if ((xMundo + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                    (xMundo - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                    (yMundo + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                    (yMundo - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla))
            {
                g2.drawImage(casillas[numCasilla].image, xPantalla, yPantalla, pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);
            }
            colMundo++;


            if (colMundo == pJuego.maxCol) {
                colMundo = 0;

                filaMundo++;

            }
        }

    }

    public void cargarMapa() {
        try {
            FileReader fr = new FileReader("mapas/MapaBase.txt");
            BufferedReader br = new BufferedReader(fr);

            for (int fila = 0; fila < pJuego.maxJuegoFila; fila++) {
                String linea = br.readLine();
                if (linea == null) break; // Check for end of file
                for (int col = 0; col < pJuego.maxJuegoColumna; col++) {
                    String[] codigo = linea.split(" ");
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
