package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestorTeclado implements KeyListener {

    public boolean arribaPres, izqPres, drchPres, abajoPres;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codTecla = e.getKeyCode();

        if (codTecla == KeyEvent.VK_W)
        {
            arribaPres = true;
        }
        if (codTecla == KeyEvent.VK_A)
        {
            izqPres = true;
        }
        if (codTecla == KeyEvent.VK_S)
        {
            abajoPres = true;
        }
        if (codTecla == KeyEvent.VK_D)
        {
            drchPres = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codTecla = e.getKeyCode();

        if (codTecla == KeyEvent.VK_W)
        {
            arribaPres = false;
        }
        if (codTecla == KeyEvent.VK_A)
        {
            izqPres = false;
        }
        if (codTecla == KeyEvent.VK_S)
        {
            abajoPres = false;
        }
        if (codTecla == KeyEvent.VK_D)
        {
            drchPres = false;
        }
    }
}
