package main;

import entidades.Jugador;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable {

    //Tamaño de pixel y escala de los dibujos
    final int originalPixel = 32;
    final int escala = 2;
    public final int dimensionCuadro = originalPixel * escala;

    //Setup de la pantalla
    public final int maxCol = 20;
    public final int maxFil = 15;
    public final int altoPantalla = (dimensionCuadro * maxFil)/escala; //640 - 1280 pixeles
    public final int anchoPantalla = (dimensionCuadro * maxCol)/escala; // 480 - 960 pixeles

    //Dimensiones del juego
    public final int maxJuegoFila = 100;
    public final int maxJuegoColumna = 40;

    //FPS
    int fps = 60;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;



    GestorTeclado gestTec = new GestorTeclado();
    Thread gameThread;
    Jugador jugador = new Jugador(this,gestTec);


    //Constructor
    PanelJuego()
    {
        this.setPreferredSize(new Dimension(anchoPantalla,altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(gestTec);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double intervaloDibujo = (double)1000000000/fps;
        double delta =0;
        long ultimoTiempo = System.nanoTime();
        long tiempoActual;
        long contador = 0;
        int contDibujo = 0;
        while(gameThread != null)
        {

            //1º ACTUALIZAMOS INFORMACION ANTES DE DIBUJAR
            tiempoActual = System.nanoTime();
            delta += (tiempoActual - ultimoTiempo) / intervaloDibujo;
            contador += tiempoActual - ultimoTiempo;
            ultimoTiempo = tiempoActual;

            if (delta > 1)
            {
                update();
                repaint();
                delta--;
                contDibujo++;
            }

            if (contador > 1000000000)
            {
                System.out.println("FPS: "+contDibujo);
                contDibujo = 0;
                contador = 0;
            }
            //2º DIBUJAMOS YA CON LA INFORMACION ACTULAIZADA
        }
    }

    public void update()
    {

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.dispose();
    }
}
