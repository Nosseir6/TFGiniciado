package casilla;

import main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class GestorCasillas
{
    PanelJuego pj;
    Casilla[] casillas;


    public GestorCasillas(PanelJuego panelJuego)
    {
        this.pj = panelJuego;

        casillas = new Casilla[10];

        getCasillaImage();
    }

    public void getCasillaImage()
    {
        try {
            casillas[0] = new Casilla();
            casillas[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/marble_wall1.png"));
            casillas[1] = new Casilla();
            casillas[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/marble_floor1.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
      int col = 0;
      int fila = 0;
      int x = 0;
      int y = 0;

      while (col < pj.maxCol && fila < pj.maxFil)
      {
          g2.drawImage(casillas[0].image, x, y, pj.dimensionCasillas, pj.dimensionCasillas, null);
          col++;
          x += pj.dimensionCasillas;

          if (col == pj.maxCol)
          {
              col = 0;
              x = 0;
              fila++;
              y += pj.dimensionCasillas;
          }
      }

    }

}
