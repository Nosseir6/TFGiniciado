package main;

import casilla.GestorCasillas;
import entidades.Jugador;
import entidades.Entidad;
import objetos.SuperObjeto;
import sonido.Sonido;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el panel de juego donde se renderiza y actualiza el juego.
 */
public class PanelJuego extends JPanel implements Runnable {

    // Tamaño original de los píxeles y escala de los dibujos
    final int originalPixel = 32;
    public final int escala = 2;
    public final int dimensionCasillas = originalPixel * escala;

    // Configuración de la pantalla
    public final int maxCol = 20;
    public final int maxFil = 15;
    public final int altoPantalla = (dimensionCasillas * maxFil); // 640 - 1280 píxeles
    public final int anchoPantalla = (dimensionCasillas * maxCol); // 480 - 960 píxeles

    // Dimensiones del juego
    public final int maxJuegoFila = 40;
    public final int maxJuegoColumna = 20;

    // FPS (fotogramas por segundo)
    int fps = 60;

    // Mensaje de inicio de juego
    public boolean inicioJuego = true;

    // Componentes del juego
    public InterfazUsuario iu = new InterfazUsuario(this);
    public GestorColisiones gestColisiones = new GestorColisiones(this);
    GestorCasillas gestCasillas = new GestorCasillas(this);
    GestorTeclado gestTec = new GestorTeclado();
    GestorEntidades gestEnt = new GestorEntidades(this);
    Thread gameThread;
    Sonido sonido = new Sonido();
    public Jugador jugador = new Jugador(this, gestTec);
    public SuperObjeto[] objetos = new SuperObjeto[15];
    public Entidad[] npcs = new Entidad[10];
    public Entidad[] monstruos = new Entidad[30];
    public int contadorMensajeInicio = 0;

    // Constructor
    PanelJuego() {
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(gestTec);
        this.setFocusable(true);
    }

    /**
     * Configura el juego inicializando objetos, NPCs y monstruos.
     */
    public void setUpJuego() {
        gestEnt.setObjeto();
        gestEnt.setNPC();
        gestEnt.setMonstruo();
        playMusica(0);
        iu.setDialogo();
    }

    /**
     * Inicia el hilo de juego.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double intervaloDeDibujo = (double) 1000000000 / fps;
        double delta = 0;
        long ultimoTiempo = System.nanoTime();
        long tiempoActual;

        while (gameThread != null) {
            tiempoActual = System.nanoTime();
            delta += (tiempoActual - ultimoTiempo) / intervaloDeDibujo;
            ultimoTiempo = tiempoActual;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Actualiza el estado del juego.
     */
    public void update() {
        // Actualizar jugador
        jugador.update();

        if (contadorMensajeInicio < 500)
            contadorMensajeInicio++;
        else
            inicioJuego = false;

        // Actualizar NPCs
        for (int i = 0; i < npcs.length; i++) {
            if (npcs[i] != null)
                npcs[i].update();
        }

        // Actualizar monstruos
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null) {
                monstruos[i].update();
            }
        }
    }

    /**
     * Método para renderizar los elementos del juego.
     * @param g Objeto Graphics utilizado para dibujar.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Dibujar mapa
        gestCasillas.draw(g2);

        // Dibujar objetos
        for (SuperObjeto objeto : objetos) {
            if (objeto != null)
                objeto.draw(g2, this);
        }

        // Dibujar NPCs
        for (Entidad npc : npcs) {
            if (npc != null)
                npc.draw(g2);
        }

        // Dibujar monstruos
        for (Entidad mons : monstruos) {
            if (mons != null)
                mons.draw(g2);
        }

        // Dibujar jugador
        jugador.draw(g2);

        // Dibujar interfaz de usuario
        iu.draw(g2);

        // Dibujar mensaje de inicio de juego
        g2.dispose();
    }

    /**
     * Reproduce música de fondo.
     * @param i Índice de la pista de audio.
     */
    public void playMusica(int i) {
        sonido.setFile(i);
        sonido.setVolume(0.25f); // Ajusta el volumen al 25%
        sonido.play();
        sonido.loop();
    }

    /**
     * Detiene la reproducción de música de fondo.
     */
    public void stopMusic() {
        sonido.stop();
    }

    /**
     * Reproduce efectos de sonido.
     * @param i Índice del efecto de sonido.
     */
    public void playEfectos(int i) {
        sonido.setFile(i);
        sonido.play();
    }
}
