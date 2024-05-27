package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La clase GestorTeclado implementa la interfaz KeyListener para manejar eventos de teclado.
 */
public class GestorTeclado implements KeyListener {

    /** Booleano que indica si la tecla de arriba está presionada. */
    public boolean arribaPres;
    /** Booleano que indica si la tecla de izquierda está presionada. */
    public boolean izqPres;
    /** Booleano que indica si la tecla de derecha está presionada. */
    public boolean drchPres;
    /** Booleano que indica si la tecla de abajo está presionada. */
    public boolean abajoPres;

    @Override
    public void keyTyped(KeyEvent e) {
        // No se utiliza en este contexto
    }

    /**
     * Maneja el evento de tecla presionada.
     * @param e El evento de tecla presionada.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int codTecla = e.getKeyCode();

        // Verifica qué tecla se presionó y establece el booleano correspondiente
        if (codTecla == KeyEvent.VK_W) {
            arribaPres = true;
        }
        if (codTecla == KeyEvent.VK_A) {
            izqPres = true;
        }
        if (codTecla == KeyEvent.VK_S) {
            abajoPres = true;
        }
        if (codTecla == KeyEvent.VK_D) {
            drchPres = true;
        }
    }

    /**
     * Maneja el evento de tecla liberada.
     * @param e El evento de tecla liberada.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int codTecla = e.getKeyCode();

        // Verifica qué tecla se liberó y establece el booleano correspondiente
        if (codTecla == KeyEvent.VK_W) {
            arribaPres = false;
        }
        if (codTecla == KeyEvent.VK_A) {
            izqPres = false;
        }
        if (codTecla == KeyEvent.VK_S) {
            abajoPres = false;
        }
        if (codTecla == KeyEvent.VK_D) {
            drchPres = false;
        }
    }
}
