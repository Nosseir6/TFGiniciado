package main;

import objetos.Corazon;
import objetos.Llave;
import objetos.SuperObjeto;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * La clase InterfazUsuario gestiona la interfaz gráfica del usuario en el juego.
 */
public class InterfazUsuario {

    /** Gráficos 2D. */
    Graphics2D g2;
    /** Panel del juego. */
    PanelJuego pJuego;
    /** Fuente Arial de tamaño 40. */
    Font arial_40;
    /** Imagen del buffer. */
    BufferedImage buffImg;
    /** Fuente Comic Sans de tamaño 15. */
    Font comic_sans15;
    /** Fuente Comic Sans de tamaño 30. */
    Font comic_sans30;

    /** Booleano que indica si hay un mensaje en pantalla. */
    public boolean mensajeOn = false;
    /** El mensaje a mostrar en pantalla. */
    public String mensaje = "";
    /** Contador para el mensaje. */
    int contMensaje = 0;
    /** Arreglo de diálogos. */
    public String[] dialogo = new String[6];

    /** Booleano que indica si el juego ha terminado. */
    public boolean juegoAcabado = false;
    /** Imagen de un corazón completo. */
    BufferedImage corazonComp;
    /** Imagen de medio corazón. */
    BufferedImage medioCor;
    /** Imagen de un corazón vacío. */
    BufferedImage corazonVacio;

    /**
     * Constructor de la clase InterfazUsuario.
     * @param pJuego El panel del juego.
     */
    public InterfazUsuario(PanelJuego pJuego) {
        this.pJuego = pJuego;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        comic_sans15 = new Font("Comic Sans MS", Font.ITALIC, 15);
        comic_sans30 = new Font("Comic Sans MS", Font.ITALIC, 30);
        Llave llave = new Llave(pJuego);
        buffImg = llave.imagen;

        // CORAZONES
        SuperObjeto corazon = new Corazon(pJuego);
        corazonComp = corazon.imagen;
        medioCor = corazon.imagen2;
        corazonVacio = corazon.imagen3;
    }

    /**
     * Muestra un mensaje en la interfaz de usuario.
     * @param texto El texto del mensaje.
     */
    public void mostrarMensaje(String texto) {
        mensaje = texto;
        mensajeOn = true;
    }

    /**
     * Configura el diálogo del juego.
     */
    public void setDialogo() {
        dialogo[0] = "Hola aventurero, bienvenido a la mazmorra.";
        dialogo[1] = "Para completarla has de llegar al altar al final de la misma,";
        dialogo[2] = "puedes elegir si luchar o correr, la elección es tuya.";
        dialogo[3] = "Ten cuidado si decides luchar pues hay poderosos enemigos.";
        dialogo[4] = "Arma te de valor y suerte en tu aventura";
        dialogo[5] = "QUE LA LUZ TE GUIE";
    }

    /**
     * Dibuja la interfaz de usuario en pantalla.
     * @param g2 Gráficos 2D.
     */
    public void draw(Graphics2D g2) {
        if (pJuego.inicioJuego) {
            dibujarPantallaDialogo(g2);
        }

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString(" x " + pJuego.jugador.numLlaves, pJuego.anchoPantalla - 2 * pJuego.dimensionCasillas, 60);
        g2.drawImage(buffImg, pJuego.anchoPantalla - 3 * pJuego.dimensionCasillas, pJuego.dimensionCasillas / 5,
                pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);

        if (juegoAcabado) {
            if (pJuego.jugador.vida == 0) {
                // Jugador muerto
                String texto;
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                texto = "Has muerto";
                int mensajeLong = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
                int x = pJuego.anchoPantalla / 2 - mensajeLong / 2;
                int y = pJuego.altoPantalla / 2 - (pJuego.dimensionCasillas * 3);
                g2.drawString(texto, x, y);
                pJuego.gameThread = null;
            } else {
                // Jugador ha completado el juego
                String texto;
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                texto = "Felicidades has terminado el juego";
                int mensajeLong = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
                int x = pJuego.anchoPantalla / 2 - mensajeLong / 2;
                int y = pJuego.altoPantalla / 2 - (pJuego.dimensionCasillas * 3);
                g2.drawString(texto, x, y);
                pJuego.gameThread = null;
            }
        } else {
            // Medición de tiempo que le lleva a el jugador terminar el juego
            // tiempoJuego += (double) 1/60;
            // g2.drawString("Tiempo: " + df.format(tiempoJuego), pJuego.dimensionCasillas*16,60);

            // Mensaje
            if (mensajeOn) {
                g2.setFont(comic_sans15);
                g2.drawString(mensaje, pJuego.anchoPantalla / 2 - pJuego.dimensionCasillas - 10,
                        pJuego.altoPantalla / 2 - pJuego.dimensionCasillas);
                contMensaje++;
                if (contMensaje >= 120) {
                    contMensaje = 0;
                    mensajeOn = false;
                }
            }
        }

        dibujarVida(g2);
    }

    /**
     * Dibuja la pantalla de diálogo.
     * @param g2 Gráficos 2D.
     */
    public void dibujarPantallaDialogo(Graphics2D g2) {
        // Configuración de la ventana de diálogo
        int x = pJuego.dimensionCasillas * 2;
        int y = pJuego.dimensionCasillas / 2;
        int ancho = pJuego.anchoPantalla - (pJuego.dimensionCasillas) * 4;
        int alto = pJuego.dimensionCasillas * 5;
        // Dibujar el contenido de la ventana de diálogo
        dibujarSubDialogo(g2,x, y, ancho, alto);

        g2.setFont(comic_sans30);
        g2.setColor(Color.WHITE);
        x += pJuego.dimensionCasillas;
        y += pJuego.dimensionCasillas;

        for (String s : dialogo) {
            if (s != null) {
                g2.drawString(s, x, y);
                y += 45;
            }
        }
    }

    /**
     * Dibuja el sub-diálogo.
     * @param g2 Gráficos 2D.
     * @param x Coordenada x.
     * @param y Coordenada y.
     * @param ancho Ancho del sub-diálogo.
     * @param alto Alto del sub-diálogo.
     */
    public void dibujarSubDialogo(Graphics2D g2, int x, int y, int ancho, int alto) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, ancho, alto, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, ancho - 10, alto - 10, 25, 25);
    }

    /**
     * Dibuja la barra de vida del jugador en la interfaz.
     * @param g2 Gráficos 2D.
     */
    public void dibujarVida(Graphics2D g2) {
        int x = pJuego.dimensionCasillas / 2;
        int y = pJuego.dimensionCasillas / 2;

        int i = 0;

        // Dibujar vida máxima
        while (i < pJuego.jugador.vidaMax / 2) {
            g2.drawImage(corazonVacio, x, y, null);
            i++;
            x += pJuego.dimensionCasillas;
        }

        // Resetear valores
        x = pJuego.dimensionCasillas / 2;
        y = pJuego.dimensionCasillas / 2;
        i = 0;

        // Dibujar vida actual
        while (i < pJuego.jugador.vida) {
            g2.drawImage(medioCor, x, y, null);
            i++;
            if (i < pJuego.jugador.vida) {
                g2.drawImage(corazonComp, x, y, null);
            }
            i++;
            x += pJuego.dimensionCasillas;
        }
    }

}

