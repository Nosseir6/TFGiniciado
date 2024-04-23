package entidades;

import main.GestorTeclado;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends SuperEntidad
{

    PanelJuego pJuego;
    GestorTeclado gestTec;

    public Jugador (PanelJuego pJuego, GestorTeclado gestTec)
    {
        this.pJuego = pJuego;
        this.gestTec = gestTec;
        setValoresPorDefecto();
    }

    public void setValoresPorDefecto()
    {
        x = pJuego.altoPantalla/2;
        y = pJuego.anchoPantalla/2;
        direction = "abajo";
    }

    public void setJugadorImagen()
    {
        try
        {
            basica = ImageIO.read(getClass().getResourceAsStream("/jugadores/basica"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if (gestTec.arribaPres)
        {
            direction = "arriba";
            y -= speed;
        }
        if (gestTec.izqPres)
        {
            direction = "izquierda";
            x -= speed;
        }
        if (gestTec.drchPres)
        {
            direction = "derecha";
            x += speed;
        }
        if (gestTec.abajoPres)
        {
            direction = "abajo";
            y += speed;
        }
    }
    public void draw(Graphics2D graphics2D)
    {

        BufferedImage imagen = null;

        switch (direction)
        {
            case "up"->
            {

            }
        }
    }
}
