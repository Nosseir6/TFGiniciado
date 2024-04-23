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
        setJugadorImagen();
    }

    public void setValoresPorDefecto()
    {
        x = 100;
        y = 100;
        velocidad = 3;
        direccion = "abajo"; //ASIGNACION DE VALOR POR DEFECTO PARA SABER COMO SE VA APARECER DIBUAJADO EL PERSONAJE LA PRIMERA VEZ
    }

    public void setJugadorImagen()
    {
        try
        {
            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ABAJO
            imagenAbajo = ImageIO.read(getClass().getResourceAsStream("/jugador/adelante/Frontal.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/jugador/adelante/MovimientoAdelante1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/jugador/adelante/MovimientoAdelante2.png"));
            abajo3 = ImageIO.read(getClass().getResourceAsStream("/jugador/adelante/MovimientoAdelante3.png"));
            abajo4 = ImageIO.read(getClass().getResourceAsStream("/jugador/adelante/MovimientoAdelante4.png"));
            imagenArriba = ImageIO.read(getClass().getResourceAsStream("/jugador/atras/Espalda.png"));
            imagenDrch = ImageIO.read(getClass().getResourceAsStream("/jugador/derecha/Derecha.png"));
            imagenIzq = ImageIO.read(getClass().getResourceAsStream("/jugador/izquierda/Izquierda.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update(double tiempoDelta)
    {
        if (gestTec.arribaPres || gestTec.abajoPres || gestTec.drchPres || gestTec.izqPres)
        {
            if (gestTec.arribaPres)
            {
                y -= (velocidad * tiempoDelta); // Multiplicar por tiempoDelta
                direccion = "arriba";
            }
            else if (gestTec.izqPres)
            {
                x -= (velocidad * tiempoDelta); // Multiplicar por tiempoDelta
                direccion = "izquierda";
            }
            else if (gestTec.drchPres)
            {
                x += (velocidad * tiempoDelta); // Multiplicar por tiempoDelta
                direccion = "derecha";
            }
            else if (gestTec.abajoPres)
            {
                y += (velocidad * tiempoDelta); // Multiplicar por tiempoDelta
                direccion = "abajo";
            }
        }
    }

    public void draw(Graphics2D g2)
    {

        BufferedImage imagen = switch (direccion) {
            case "arriba" -> imagenArriba;
            case "abajo" -> imagenAbajo;
            case "derecha" -> imagenDrch;
            case "izquierda" -> imagenIzq;
            default -> null;
        };

        g2.drawImage(imagen,x,y,pJuego.dimensionCuadro,pJuego.dimensionCuadro, null);

    }
}
