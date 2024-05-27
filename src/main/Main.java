package main;

import javax.swing.*;
import java.awt.*;

/**
 * Clase principal que contiene el método de inicio de la aplicación.
 */
public class Main {
    /**
     * Método principal de inicio de la aplicación.
     * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Crear una nueva ventana
        JFrame window = new JFrame();
        // Configurar el comportamiento de la ventana al cerrarla
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Evitar que la ventana sea redimensionable
        window.setResizable(false);
        // Establecer el título de la ventana
        window.setTitle("Mi juego");

        // Crear un nuevo panel de juego
        PanelJuego pJuego = new PanelJuego();
        // Agregar el panel de juego a la ventana
        window.add(pJuego);

        // Ajustar el tamaño de la ventana para que se adapte al contenido
        window.pack();

        // Centrar la ventana en la pantalla
        window.setLocationRelativeTo(null);
        // Hacer visible la ventana
        window.setVisible(true);

        // Configurar y empezar el hilo de juego del panel de juego
        pJuego.setUpJuego();
        pJuego.startGameThread();
    }
}
