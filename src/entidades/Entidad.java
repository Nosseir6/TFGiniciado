package entidades;

import interfaces.Direcciones;
import main.PanelJuego;
import main.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * La clase Entidad representa una entidad en el juego, que puede ser un jugador, un NPC, un monstruo, etc.
 * Gestiona la posición, la velocidad, las imágenes de la entidad, así como la lógica de movimiento y colisión.
 */
public class Entidad {

    PanelJuego pJuego;

    public int posMundoX, posMundoY;
    public int velocidad;
    public BufferedImage imagenAbajo, abajo1, abajo2, abajo3, abajo4, abajo5, abajo6;
    public BufferedImage imagenArriba, arriba1, arriba2, arriba3, arriba4, arriba5, arriba6;
    public BufferedImage imagenDrch, drch1, drch2, drch3, drch4, drch5, drch6;
    public BufferedImage imagenIzq, izq1, izq2, izq3, izq4, izq5, izq6;

    public Direcciones direccion;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int hitbox_XPorDefecto;
    public int hitbox_YPorDefecto;

    public Rectangle hitBox = new Rectangle(0, 0, 64, 64);
    public boolean hayColision = false;

    public int vidaMax;
    public int vida;

    public int contadorAcciones = 0;
    public boolean invencibilidad = false;
    public int contInven = 0;

    /**
     * Constructor para la clase Entidad.
     * @param pJuego La referencia al objeto PanelJuego que contiene la configuración del juego.
     */
    public Entidad(PanelJuego pJuego) {
        this.pJuego = pJuego;
    }

    /**
     * Dibuja la entidad en la pantalla.
     * @param g2 El objeto Graphics2D utilizado para dibujar.
     */
    public void draw(Graphics2D g2) {
        int xPantalla = posMundoX - pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla;
        int yPantalla = posMundoY - pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla;

        BufferedImage[] imagenesArriba = {imagenArriba, arriba1, arriba2, arriba3, arriba4, arriba5, arriba6};
        BufferedImage[] imagenesAbajo = {imagenAbajo, abajo1, abajo2, abajo3, abajo4, abajo5, abajo6};
        BufferedImage[] imagenesIzquierda = {imagenIzq, izq1, izq2, izq3, izq4, izq5, izq6};
        BufferedImage[] imagenesDerecha = {imagenDrch, drch1, drch2, drch3, drch4, drch5, drch6};

        if ((posMundoX + pJuego.dimensionCasillas > pJuego.jugador.posMundoX - pJuego.jugador.posXPantalla) ||
                (posMundoX - pJuego.dimensionCasillas < pJuego.jugador.posMundoX + pJuego.jugador.posXPantalla) ||
                (posMundoY + pJuego.dimensionCasillas > pJuego.jugador.posMundoY - pJuego.jugador.posYPantalla) ||
                (posMundoY - pJuego.dimensionCasillas < pJuego.jugador.posMundoY + pJuego.jugador.posYPantalla)) {

            BufferedImage imagen = null;

            switch (direccion) {
                case ABAJO -> imagen = imagenesAbajo[spriteNum];
                case ARRIBA -> imagen = imagenesArriba[spriteNum];
                case IZQUIERDA -> imagen = imagenesIzquierda[spriteNum];
                case DERECHA -> imagen = imagenesDerecha[spriteNum];
            }

            g2.drawImage(imagen, xPantalla, yPantalla, pJuego.dimensionCasillas, pJuego.dimensionCasillas, null);
        }
    }

    /**
     * Configura la acción de la entidad.
     * Este método debe ser sobrescrito por subclases para definir acciones específicas.
     */
    public void setAccion() {}

    /**
     * Actualiza el estado de la entidad, incluyendo el movimiento y la detección de colisiones.
     */
    public void update() {
        setAccion();

        hayColision = false;
        pJuego.gestColisiones.comprobarCasilla(this);
        pJuego.gestColisiones.colisionObjetos(this, false);
        pJuego.gestColisiones.comprobarEntidad(this, pJuego.monstruos);
        pJuego.gestColisiones.comprobarEntidad(this, pJuego.npcs);
        pJuego.gestColisiones.comprobarJugador(this);

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum + 1) % 7;
            spriteCounter = 0;
        }

        if (!hayColision) {
            switch (direccion) {
                case ARRIBA -> posMundoY -= velocidad;
                case ABAJO -> posMundoY += velocidad;
                case DERECHA -> posMundoX += velocidad;
                case IZQUIERDA -> posMundoX -= velocidad;
            }
        } else {
            spriteNum = 0;
            spriteCounter = 0;
        }
    }

    /**
     * Configura una imagen de una entidad a partir de una ruta de imagen.
     * @param rutaImagen La ruta de la imagen.
     * @return La imagen configurada y escalada.
     */
    public BufferedImage setup(String rutaImagen) {
        Util util = new Util();
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(rutaImagen));
            imagen = util.escalada(imagen, pJuego.dimensionCasillas, pJuego.dimensionCasillas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
}
