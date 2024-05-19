package entidades;

import interfaces.Direcciones;

import java.awt.image.BufferedImage;

public class SuperEntidad {
    public int posMundoX, posMundoY;
    public int velocidad;
    public BufferedImage imagenAbajo, abajo1, abajo2, abajo3, abajo4;
    public BufferedImage imagenArriba, arriba1, arriba2, arriba3, arriba4;
    public BufferedImage imagenDrch, drch1, drch2, drch3, drch4;
    public BufferedImage imagenIzq, izq1, izq2, izq3, izq4;
    public Direcciones direccion;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
