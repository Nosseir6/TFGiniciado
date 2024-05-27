package main;

import casilla.GestorCasillas;
import entidades.Jugador;
import objetos.SuperObjeto;
import sonido.Sonido;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable {

    //Tamaño de pixel y escala de los dibujos
    final int originalPixel = 32;
    public final int escala = 2;
    public final int dimensionCasillas = originalPixel * escala;

    //Setup de la pantalla
    public final int maxCol = 20;
    public final int maxFil = 15;
    public final int altoPantalla = (dimensionCasillas * maxFil); //640 - 1280 pixeles
    public final int anchoPantalla = (dimensionCasillas * maxCol); // 480 - 960 pixeles

    //Dimensiones del juego
    public final int maxJuegoFila = 40;
    public final int maxJuegoColumna = 20;

    //FPS
    int fps = 60;

    //Mensaje de Inicio de Juego
    public boolean inicioJuego = true;


    public InterfazUsuario iu = new InterfazUsuario(this);
    public GestorColisiones gestColisiones = new GestorColisiones(this);
    GestorCasillas gestCasillas = new GestorCasillas(this);
    GestorTeclado gestTec = new GestorTeclado();
    GestorObjetos gestObj = new GestorObjetos(this);
    Thread gameThread;
    Sonido sonido = new Sonido();
    public Jugador jugador = new Jugador(this,gestTec);
    public SuperObjeto[] objetos = new SuperObjeto[15];
    public int contadorMensajeInicio = 0;



    //Constructor
    PanelJuego()
    {
        this.setPreferredSize(new Dimension(anchoPantalla,altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(gestTec);
        this.setFocusable(true);
    }

    public void setUpJuego()
    {
        gestObj.setObjeto();
        playMusica(0);
        iu.setDialogo();
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
    delta del metodo run ya que ella es la que se encarga de eso*/
    public void update()
    {
        //ACTUALIZAR JUGADOR
        jugador.update();

        if (contadorMensajeInicio < 500)
            contadorMensajeInicio++;
        else
            inicioJuego = false;

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //DIBUJAR MAPA
        gestCasillas.draw(g2);

        //DIBUJAR OBJETOS
        for (SuperObjeto objeto : objetos)
        {
            if (objeto != null)
                objeto.draw(g2,this);
        }

        //DIBUJAR JUGADOR
        jugador.draw(g2);

        //DIBUJAR INTERFAZ USUARIO

        iu.draw(g2);

        //DIBUJAR MENSAJE DE INICIO DE JUEGO

        g2.dispose();
    }

    public void playMusica(int i) {
        sonido.setFile(i);
        sonido.setVolume(0.25f); // Ajusta el volumen al 50%
        sonido.play();
        sonido.loop();
    }


    public void stopMusic()
    {
        sonido.stop();
    }

    public void playEfectos(int i)
    {
        sonido.setFile(i);
        sonido.play();
    }
}
