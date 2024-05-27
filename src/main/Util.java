package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Util
{
    public BufferedImage escalada(BufferedImage original, int ancho, int alto)
    {
        BufferedImage escalada = new BufferedImage(ancho,alto, original.getType());
        Graphics2D g2 = escalada.createGraphics();
        g2.drawImage(original,0,0,ancho, alto,null);
        g2.dispose();

        return escalada;
    }
}
