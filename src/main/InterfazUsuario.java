package main;

import objetos.Llave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class InterfazUsuario
{
    Graphics2D g2;
    PanelJuego pJuego;
    Font arial_40;
    BufferedImage buffImg;
    Font comic_sans15;
    Font comic_sans30;

    public boolean mensajeOn = false;
    public String mensaje = "";
    int contMensaje = 0;
    public String[] dialogo = new String[6];

    public boolean juegoAcabado = false;
    double tiempoJuego;
    DecimalFormat df = new DecimalFormat("#0.00");


    public InterfazUsuario(PanelJuego pJuego)
    {
        this.pJuego = pJuego;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        comic_sans15 = new Font("Comic Sans MS",Font.ITALIC,15);
        comic_sans30 = new Font("Comic Sans MS",Font.ITALIC,30);
        Llave llave = new Llave();
        buffImg = llave.imagen;
    }

    public void mostrarMensaje(String texto)
    {
        mensaje = texto;
        mensajeOn = true;
    }

    public void setDialogo()
    {
        dialogo[0] = "Hola aventurero, bienvenido a la mazmorra.";
        dialogo[1] = "Para completarla has de llegar al altar al final de la misma,";
        dialogo[2] = "puedes elegir si luchar o correr, la eleccion es tuya.";
        dialogo[3] = "Ten cuidado si decides luchar pues hay poderosos enemigos.";
        dialogo[4] = "Armate de valor y suerte en tu aventura";
        dialogo[5] = "QUE LA LUZ TE GUIE";

    }

    public void draw(Graphics2D g2)
    {


        if (pJuego.inicioJuego)
        {
            dibujarPantallaDialogo(g2);
        }

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString(" x " +pJuego.jugador.numLlaves, 70, 60);
        g2.drawImage(buffImg, pJuego.dimensionCasillas/5, pJuego.dimensionCasillas/5, pJuego.dimensionCasillas,pJuego.dimensionCasillas, null);

        if (juegoAcabado)
        {
            String texto;

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            texto = "Felicidades has terminado el juego";
            int mensajeLong = (int)g2.getFontMetrics().getStringBounds(texto , g2).getWidth();// PARA OBETENER LONGITUD DEL TEXTO
            int x = pJuego.anchoPantalla/2 - mensajeLong/2;
            int y = pJuego.altoPantalla/2 - (pJuego.dimensionCasillas * 3);
            g2.drawString(texto, x, y);

            pJuego.gameThread = null;

        }
        else
        {
            //Medicion de tiempo que le lleva a el jugador terminar el juego
//          tiempoJuego += (double) 1/60;
//          g2.drawString("Tiempo: " + df.format(tiempoJuego), pJuego.dimensionCasillas*16,60);

            //MENSAJE
            if (mensajeOn)
            {
                g2.setFont(comic_sans15);
                g2.drawString(mensaje,pJuego.anchoPantalla/2 - pJuego.dimensionCasillas -10, pJuego.altoPantalla/2 - pJuego.dimensionCasillas);
                contMensaje++;

                if (contMensaje >= 120)
                {
                    contMensaje = 0;
                    mensajeOn = false;
                }
            }
        }
    }

    public void dibujarPantallaDialogo(Graphics2D g2)
    {
        //CONFIGURACION DE VENTANA DE DIALOGO
        int x = pJuego.dimensionCasillas * 2;
        int y = pJuego.dimensionCasillas/2;
        int ancho = pJuego.anchoPantalla - (pJuego.dimensionCasillas)*4;
        int alto = pJuego.dimensionCasillas * 5;
        //DIBUJAR EL CONTENIDO DE LA VENTANA DE DIALOGO
        dibujarSubDialogo(g2, x, y, ancho, alto);

        g2.setFont(comic_sans30);
        g2.setColor(Color.WHITE);
        x += pJuego.dimensionCasillas;
        y += pJuego.dimensionCasillas;

        for (String s : dialogo)
        {
            if (s != null)
            {
                g2.drawString(s, x, y);
                y += 45;
            }
        }
    }

    public void dibujarSubDialogo(Graphics2D g2, int x, int y, int ancho, int alto)
    {
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, ancho, alto, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, ancho-10, alto-10, 25, 25);
    }

    public void obetnerMensaje(String rutaTexto)
    {

    }
}
