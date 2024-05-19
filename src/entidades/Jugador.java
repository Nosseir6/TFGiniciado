package entidades;

import interfaces.Direcciones;
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

    public final int posXPantalla;
    public final int posYPantalla;

    public Jugador (PanelJuego pJuego, GestorTeclado gestTec)
    {
        this.pJuego = pJuego;
        this.gestTec = gestTec;

        posXPantalla = pJuego.anchoPantalla/2 - (pJuego.dimensionCasillas);
        posYPantalla = pJuego.altoPantalla/2 - (pJuego.dimensionCasillas/2);
        setValoresPorDefecto();
        setJugadorImagen();
    }

    public void setValoresPorDefecto()
    {
        posMundoX = 500;
        posMundoY = 100;
        velocidad = 3;
        direccion = Direcciones.ABAJO; //ASIGNACION DE VALOR POR DEFECTO PARA SABER COMO SE VA APARECER DIBUAJADO EL PERSONAJE LA PRIMERA VEZ
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
            izq1 = ImageIO.read(getClass().getResourceAsStream("/jugador/izquierda/MovimientoIzq1.png"));
            izq2 = ImageIO.read(getClass().getResourceAsStream("/jugador/izquierda/MovimientoIzq2.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public void update()
    {
        if (gestTec.arribaPres || gestTec.abajoPres || gestTec.drchPres || gestTec.izqPres)
        {
            if (gestTec.arribaPres)
            {
                posMundoY -= velocidad; // Multiplicar por tiempoDelta
                direccion = Direcciones.ARRIBA;
            }
            else if (gestTec.izqPres)
            {
                posMundoX -= velocidad; // Multiplicar por tiempoDelta
                direccion = Direcciones.IZQUIERDA;
            }
            else if (gestTec.drchPres)
            {
                posMundoX += velocidad; // Multiplicar por tiempoDelta
                direccion = Direcciones.DERECHA;
            }
            else if (gestTec.abajoPres)
            {
                posMundoY += velocidad; // Multiplicar por tiempoDelta
                direccion = Direcciones.ABAJO;
            }

            spriteCounter++;
            if (spriteCounter > 12)
            {
                if (gestTec.abajoPres)
                    spriteNum = (spriteNum + 1) % 5;
                if (gestTec.izqPres)
                    spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
        }

        else
        {
            spriteNum = 0;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage[] imagenesArriba = {imagenArriba, arriba1, arriba2, arriba3, arriba4};
        BufferedImage[] imagenesAbajo = {imagenAbajo, abajo1, abajo2, abajo3, abajo4};
        BufferedImage[] imagenesIzquierda = {imagenIzq, izq1, izq2, izq3, izq4};
        BufferedImage[] imagenesDerecha = {imagenDrch, drch1, drch2, drch3, drch4};

        BufferedImage imagen = null;

        switch (direccion)
        {
            case ABAJO -> imagen = imagenesAbajo[spriteNum];
            case ARRIBA -> imagen = imagenesArriba[spriteNum];
            case IZQUIERDA -> imagen = imagenesIzquierda[spriteNum];
            case DERECHA -> imagen = imagenesDerecha[spriteNum];
        }

        g2.drawImage(imagen, posXPantalla, posYPantalla,pJuego.dimensionCasillas,pJuego.dimensionCasillas, null);

    }
}
