package entidades;

import interfaces.Direcciones;
import main.PanelJuego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperEntidad {
    public int posMundoX, posMundoY;
    public int velocidad;
    public BufferedImage imagenAbajo, abajo1, abajo2, abajo3, abajo4, abajo5, abajo6;
    public BufferedImage imagenArriba, arriba1, arriba2, arriba3, arriba4, arriba5, arriba6;
    public BufferedImage imagenDrch, drch1, drch2, drch3, drch4, drch5, drch6;
    public BufferedImage imagenIzq, izq1, izq2, izq3, izq4, izq5, izq6;
    public Direcciones direccion;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int hitbox_XPorDefecto;
    public int hitbox_YPorDefecto;

    public Rectangle hitBox;
    public boolean hayColision = false;

    public int vidaMax;
    public int vida;

    int contadorAcciones = 0;
}
