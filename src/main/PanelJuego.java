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
        double intervaloDeDibujo =(double) 1000000000/fps;
        double delta = 0;
        long ultimoTiempo = System.nanoTime();
        long tiempoActual;

        while (gameThread != null)
        {
            tiempoActual = System.nanoTime();
            delta += (tiempoActual-ultimoTiempo) / intervaloDeDibujo;
            ultimoTiempo = tiempoActual;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }



    /*Problema con el ajuste de los fps, al incrementar los fps la velocidad varia lo cual es un error ya que la
    velocidad a la que se muevee un jugador deberia ser independiente de los fps para ellos hay que usar la variable
    delta del metodo run ya que ella es la que se encarga de */
    public void update()
    {
        //ACTUALIZAR JUGADOR
        jugador.update();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //DIBUJAR JUGADOR
        jugador.draw(g2);

        g2.dispose();
    }
}
