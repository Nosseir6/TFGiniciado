package casilla;

import main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestorCasillas
{
    PanelJuego pj;
    Casilla[] casillas;
    int[][] mapaJuego;


    public GestorCasillas(PanelJuego panelJuego)
    {
        this.pj = panelJuego;

        casillas = new Casilla[10];
        mapaJuego = new int[pj.maxJuegoColumna][pj.maxJuegoFila];

        getCasillaImage();
        cargarMapa();
    }

    public void getCasillaImage()
    {
        try {
            casillas[0] = new Casilla();
            casillas[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/marble_floor1.png"));
            casillas[1] = new Casilla();
            casillas[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/marble_wall1.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
      int colMundo = 0;
      int filaMundo = 0;


      while (colMundo < pj.maxJuegoColumna && filaMundo < pj.maxJuegoFila)
      {
          int numCasilla = mapaJuego[colMundo][filaMundo];

          int xMundo = colMundo * pj.dimensionCasillas; //posicion en el mundo
          int yMundo = filaMundo * pj.dimensionCasillas; //posicion en el mundo
          int xPantalla = xMundo - pj.jugador.posMundoX + pj.jugador.posXPantalla; //parte de la pantalla en la que lo dibujamos, se suma la posicion de la pantalla para compensar la diferencia en caso de las esquinas
          int yPantalla = yMundo - pj.jugador.posMundoY + pj.jugador.posYPantalla;

          g2.drawImage(casillas[numCasilla].image, xPantalla, yPantalla, pj.dimensionCasillas, pj.dimensionCasillas, null);
          colMundo++;


          if (colMundo == pj.maxCol)
          {
              colMundo = 0;

              filaMundo++;

          }
      }

    }

    public void cargarMapa()
    {
        try
        {
            FileReader fr = new FileReader("mapas/MapaBase.txt");
            BufferedReader br = new BufferedReader(fr);

            for (int fila = 0; fila < pj.maxJuegoFila; fila++)
            {
                String linea = br.readLine();
                if (linea == null) break; // Check for end of file
                for (int col = 0; col < pj.maxJuegoColumna; col++)
                {
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
