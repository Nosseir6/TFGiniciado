package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjeto
{
    public BufferedImage imagen;
    public TipoObjetos nombre;
    public boolean colision = false;
    public int posMundoX, posMundoY;
    public Rectangle hitbox = new Rectangle(0,0,64,64);
    public int hitBoxDefaultX = 0;
    public int hitBoxDefaultY = 0;

    public void draw(Graphics2D g2, PanelJuego pJuego)
    {
        int xPantalla = posMundoX - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla; //parte de la pantalla en la que lo dibujamos, se suma la posicion de la pantalla para compensar la diferencia en caso de las esquinas
        int yPantalla = posMundoY - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;

        //Codigo para mejorar la eficiencia de dibujado del mapa dinuja solo si esta en la pantalla del jugador
        //En lugar de dibujar "to_do el mapa
        if ((posMundoX + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                (posMundoX - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                (posMundoY + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                (posMundoY - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla))
        {
            g2.drawImage(imagen, xPantalla, yPantalla, pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);
        }
    }
}
