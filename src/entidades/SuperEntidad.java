package entidades;

import interfaces.Direcciones;
import main.PanelJuego;
import main.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SuperEntidad {

    PanelJuego pJuego;

    public int posMundoX, posMundoY;
    public int velocidad;
    public BufferedImage imagenAbajo, abajo1, abajo2, abajo3, abajo4, abajo5, abajo6;
    public BufferedImage imagenArriba, arriba1, arriba2, arriba3, arriba4, arriba5, arriba6;
    public BufferedImage imagenDrch, drch1, drch2, drch3, drch4, drch5, drch6;
    public BufferedImage imagenIzq, izq1, izq2, izq3, izq4, izq5, izq6;
    BufferedImage[] imagenesArriba = {imagenArriba, arriba1, arriba2, arriba3, arriba4, arriba5, arriba6};
    BufferedImage[] imagenesAbajo = {imagenAbajo, abajo1, abajo2, abajo3, abajo4, abajo5, abajo6};
    BufferedImage[] imagenesIzquierda = {imagenIzq, izq1, izq2, izq3, izq4, izq5, izq6};
    BufferedImage[] imagenesDerecha = {imagenDrch, drch1, drch2, drch3, drch4, drch5, drch6};
    public Direcciones direccion;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int hitbox_XPorDefecto;
    public int hitbox_YPorDefecto;

    public Rectangle hitBox;
    public boolean hayColision = false;

    public int vidaMax;
    public int vida;

    public int contadorAcciones = 0;

    public SuperEntidad(PanelJuego pJuego)
    {
        this.pJuego = pJuego;
    }
    public void draw(Graphics2D g2)
    {
        int xPantalla = posMundoX - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla; //parte de la pantalla en la que lo dibujamos, se suma la posicion de la pantalla para compensar la diferencia en caso de las esquinas
        int yPantalla = posMundoY - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;


        if ((posMundoX + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                (posMundoX - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                (posMundoY + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                (posMundoY - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla))
        {
            BufferedImage imagen = null;

            switch (direccion)
            {
                case ABAJO -> imagen = imagenesAbajo[spriteNum];
                case ARRIBA -> imagen = imagenesArriba[spriteNum];
                case IZQUIERDA -> imagen = imagenesIzquierda[spriteNum];
                case DERECHA -> imagen = imagenesDerecha[spriteNum];
            }



            g2.drawImage(imagen, xPantalla, yPantalla, pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);
        }
    }

    public void setAccion(){}
    public void update(){
        setAccion();

        hayColision = false;
        pJuego.gestColisiones.comprobarCasilla(this);
        pJuego.gestColisiones.colisionObjetos(this,false);
        pJuego.gestColisiones.comprobarJugador(this);

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum + 1) % 7;
            spriteCounter = 0;
        }

        if (!hayColision) {
            switch (direccion) {
                case ARRIBA -> posMundoY -= velocidad; // Multiplicar por tiempoDelta
                case ABAJO -> posMundoY += velocidad; // Multiplicar por tiempoDelta
                case DERECHA -> posMundoX += velocidad; // Multiplicar por tiempoDelta
                case IZQUIERDA -> posMundoX -= velocidad; // Multiplicar por tiempoDelta
            }
        } else {
            spriteNum = 0;
            spriteCounter = 0;
        }
    }

    public BufferedImage setup(String rutaImagen)
    {
        Util util = new Util();
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(rutaImagen));
            imagen = util.escalada(imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return imagen;
    }
}
