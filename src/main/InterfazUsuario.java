package main;

import objetos.Llave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InterfazUsuario
{
    PanelJuego pJuego;
    Font arial_40;
    BufferedImage buffImg;

    public boolean mensajeOn = false;
    public String mensaje = "";
    int contMensaje = 0;

    public InterfazUsuario(PanelJuego pJuego)
    {
        this.pJuego = pJuego;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        Llave llave = new Llave();
        buffImg = llave.imagen;
    }

    public void showMensaje(String texto)
    {
        mensaje = texto;
        mensajeOn = true;
    }

    public void draw(Graphics2D g2)
    {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString(" x " +pJuego.jugador.numLlaves, 70, 60);
        g2.drawImage(buffImg, pJuego.dimensionCasillas/5, pJuego.dimensionCasillas/5, pJuego.dimensionCasillas,pJuego.dimensionCasillas, null);

        //MENSAJE
        if (mensajeOn)
        {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(mensaje,pJuego.dimensionCasillas/2, pJuego.dimensionCasillas*5);
            contMensaje++;

            if (contMensaje >= 120)
            {
                contMensaje = 0;
                mensajeOn = false;
            }
        }
    }
}
