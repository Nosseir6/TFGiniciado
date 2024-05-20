package objetos;

import main.PanelJuego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjeto
{
    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;
    public int xMundo, yMundo;

    public void draw(Graphics2D g2, PanelJuego pJuego)
    {
        int xPantalla = xMundo - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla; //parte de la pantalla en la que lo dibujamos, se suma la posicion de la pantalla para compensar la diferencia en caso de las esquinas
        int yPantalla = yMundo - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;

        //Codigo para mejorar la eficiencia de dibujado del mapa dinuja solo si esta en la pantalla del jugador
        //En lugar de dibujar "to_do el mapa
        if ((xMundo + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                (xMundo - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                (yMundo + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                (yMundo - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla))
        {
            g2.drawImage(imagen, xPantalla, yPantalla, pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);
        }
    }
}
