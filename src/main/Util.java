package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase de utilidad que proporciona métodos para manipular imágenes.
 */
public class Util {

    /**
     * Escala una imagen al tamaño especificado.
     * @param original La imagen original que se va a escalar.
     * @param ancho El ancho deseado de la imagen escalada.
     * @param alto El alto deseado de la imagen escalada.
     * @return La imagen escalada al tamaño especificado.
     */
    public BufferedImage escalada(BufferedImage original, int ancho, int alto) {
        // Crea una nueva imagen con el tamaño especificado
        BufferedImage escalada = new BufferedImage(ancho, alto, original.getType());
        Graphics2D g2 = escalada.createGraphics();

        // Dibuja la imagen original en la nueva imagen escalada
        g2.drawImage(original, 0, 0, ancho, alto, null);
        g2.dispose();

        return escalada;
    }
}
