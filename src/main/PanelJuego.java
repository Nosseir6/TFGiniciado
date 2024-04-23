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
    int fps = 120;
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
        double tiempoDeltaAcumulado = 0;
        double tiempoDelta = 0;
        long tiempoActual;
        long tiempoAnterior = System.nanoTime();

        while (gameThread != null) {
            tiempoActual = System.nanoTime();
            tiempoDelta = (tiempoActual - tiempoAnterior) / 1000000000.0; // Convertir a segundos
            tiempoDeltaAcumulado += tiempoDelta;

            while (tiempoDeltaAcumulado >= 1.0 / fps) {
                // Actualizar el estado del juego
                update(tiempoDelta);

                tiempoDeltaAcumulado -= 1.0 / fps;
            }

            // Redibujar la pantalla
            repaint();

            tiempoAnterior = tiempoActual;
        }
    }



    /*Problema con el ajuste de los fps, al incrementar los fps la velocidad varia lo cual es un error ya que la
    velocidad a la que se muevee un jugador deberia ser independiente de los fps para ellos hay que usar la variable
    delta del metodo run ya que ella es la que se encarga de */
    public void update(double tiempoDelta)
    {
        //ACTUALIZAR JUGADOR
        jugador.update(tiempoDelta);
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
