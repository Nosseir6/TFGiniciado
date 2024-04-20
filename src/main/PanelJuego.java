package main;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable {

    //Tamaño de pixel y escala de los dibujos
    final int originalPixel = 32;
    final int escala = 2;
    public final int tamañoCuadro = originalPixel * escala;

    //Setup de la pantalla
    public final int maxCol = 20;
    public final int maxFil = 15;
    public final int altoPantalla = (tamañoCuadro * maxFil)/escala; //640 - 1280 pixeles
    public final int anchoPantalla = (tamañoCuadro * maxCol)/escala; // 480 - 960 pixeles

    //Dimensiones del juego
    public final int maxJuegoFila = 100;
    public final int maxJuegoColumna = 40;

    //FPS
    int fps = 60;

    Thread gameThread;

    //Constructor
    PanelJuego()
    {
        this.setPreferredSize(new Dimension(anchoPantalla,altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double intervaloDibujo = 1000000000/fps;
        double delta =0;
        long ultimoTiempo = System.nanoTime();
        long tiempoActual;
        long contador = 0;

        while(gameThread != null)
        {
        tiempoActual = System.nanoTime();
        delta += (tiempoActual - ultimoTiempo) / intervaloDibujo;
        }
    }
}
