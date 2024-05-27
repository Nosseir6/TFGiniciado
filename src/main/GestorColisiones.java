package main;

import entidades.Entidad;

/**
 * La clase GestorColisiones se encarga de gestionar las colisiones en el juego.
 * Proporciona métodos para comprobar colisiones entre entidades, objetos y el jugador.
 */
public class GestorColisiones {

    /**
     * Referencia al objeto PanelJuego que contiene la configuración del juego.
     */
    private PanelJuego pJuego;

    /**
     * Constructor para la clase GestorColisiones.
     * @param panelJuego La referencia al objeto PanelJuego que contiene la configuración del juego.
     */
    GestorColisiones(PanelJuego panelJuego) {
        this.pJuego = panelJuego;
    }

    /**
     * Comprueba las colisiones entre la entidad y las casillas del mapa.
     * @param entidad La entidad cuyas colisiones se van a comprobar.
     */
    public void comprobarCasilla(Entidad entidad) {
        int entidadXIzqMundo = entidad.posMundoX + entidad.hitBox.x;
        int entidadXDerchMundo = entidad.posMundoX + entidad.hitBox.x + entidad.hitBox.width;
        int entidadYTopMundo = entidad.posMundoY + entidad.hitBox.y;
        int entidadYAbajoMundo = entidad.posMundoY + entidad.hitBox.y + entidad.hitBox.height;

        int entidadColIzq = entidadXIzqMundo / pJuego.dimensionCasillas;
        int entidadColDerch = entidadXDerchMundo / pJuego.dimensionCasillas;
        int entidadTopFila = entidadYTopMundo / pJuego.dimensionCasillas;
        int entidadBotFila = entidadYAbajoMundo / pJuego.dimensionCasillas;

        int casilla1, casilla2;

        switch (entidad.direccion) {
            case ARRIBA -> {
                entidadTopFila = (entidadYTopMundo - entidad.velocidad) / pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadTopFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadTopFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision) {
                    entidad.hayColision = true;
                }
            }
            case ABAJO -> {
                entidadBotFila = (entidadYAbajoMundo + entidad.velocidad) / pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadBotFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadBotFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision) {
                    entidad.hayColision = true;
                }
            }
            case IZQUIERDA -> {
                entidadColIzq = (entidadXIzqMundo - entidad.velocidad) / pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadTopFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadBotFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision) {
                    entidad.hayColision = true;
                }
            }
            case DERECHA -> {
                entidadColDerch = (entidadXDerchMundo + entidad.velocidad) / pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadTopFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadBotFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision) {
                    entidad.hayColision = true;
                }
            }
        }
    }

    /**
     * Comprueba las colisiones entre la entidad y los objetos del juego.
     * @param entidad La entidad cuyas colisiones se van a comprobar.
     * @param jugador Indica si la entidad es el jugador.
     * @return El índice del objeto con el que se ha colisionado, o 999 si no hay colisión.
     */
    public int colisionObjetos(Entidad entidad, boolean jugador) {
        int index = 999;

        for (int i = 0; i < pJuego.objetos.length; i++) {
            if (pJuego.objetos[i] != null) {
                // Obtener la posicion de la hitbox de la entidad
                entidad.hitBox.x = entidad.posMundoX + entidad.hitBox.x;
                entidad.hitBox.y = entidad.posMundoY + entidad.hitBox.y;

                // Obtener la posicion de la hitbox del objeto
                pJuego.objetos[i].hitbox.x = pJuego.objetos[i].posMundoX + pJuego.objetos[i].hitbox.x;
                pJuego.objetos[i].hitbox.y = pJuego.objetos[i].posMundoY + pJuego.objetos[i].hitbox.y;

                switch (entidad.direccion) {
                    case ARRIBA -> entidad.hitBox.y -= entidad.velocidad;
                    case ABAJO -> entidad.hitBox.y += entidad.velocidad;
                    case IZQUIERDA -> entidad.hitBox.x -= entidad.velocidad;
                    case DERECHA -> entidad.hitBox.x += entidad.velocidad;
                }

                if (entidad.hitBox.intersects(pJuego.objetos[i].hitbox)) {
                    if (pJuego.objetos[i].colision)
                        entidad.hayColision = true;
                    if (jugador)
                        index = i;
                }

                entidad.hitBox.x = entidad.hitbox_XPorDefecto;
                entidad.hitBox.y = entidad.hitbox_YPorDefecto;
                pJuego.objetos[i].hitbox.x = pJuego.objetos[i].hitBoxDefaultX;
                pJuego.objetos[i].hitbox.y = pJuego.objetos[i].hitBoxDefaultY;
            }
        }

        return index;
    }

    /**
     * Comprueba las colisiones entre la entidad y los monstruos (u otras entidades) del juego.
     * @param entidad La entidad cuyas colisiones se van a comprobar.
     * @param objetivo Array de entidades con las que se comprobarán las colisiones.
     * @return El índice de la entidad con la que se ha colisionado, o 999 si no hay colisión.
     */
    public int comprobarEntidad(Entidad entidad, Entidad[] objetivo) {
        int index = 999;

        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                // Obtener la posicion de la hitbox de la entidad
                entidad.hitBox.x = entidad.posMundoX + entidad.hitBox.x;
                entidad.hitBox.y = entidad.posMundoY + entidad.hitBox.y;

                // Obtener la posicion de la hitbox del objeto
                objetivo[i].hitBox.x = objetivo[i].posMundoX + objetivo[i].hitBox.x;
                objetivo[i].hitBox.y = objetivo[i].posMundoY + objetivo[i].hitBox.y;

                switch (entidad.direccion) {
                    case ARRIBA -> entidad.hitBox.y -= entidad.velocidad;
                    case ABAJO -> entidad.hitBox.y += entidad.velocidad;
                    case IZQUIERDA -> entidad.hitBox.x -= entidad.velocidad;
                    case DERECHA -> entidad.hitBox.x += entidad.velocidad;
                }

                if (entidad.hitBox.intersects(objetivo[i].hitBox)) {
                    if (objetivo[i] != entidad) {
                        entidad.hayColision = true;
                        index = i;
                    }
                }

                entidad.hitBox.x = entidad.hitbox_XPorDefecto;
                entidad.hitBox.y = entidad.hitbox_YPorDefecto;
                objetivo[i].hitBox.x = objetivo[i].hitbox_XPorDefecto;
                objetivo[i].hitBox.y = objetivo[i].hitbox_YPorDefecto;
            }
        }

        return index;
    }

    /**
     * Comprueba las colisiones entre la entidad y el jugador.
     * @param entidad La entidad cuyas colisiones se van a comprobar.
     */
    public void comprobarJugador(Entidad entidad) {
        // Obtener la posicion de la hitbox de la entidad
        entidad.hitBox.x = entidad.posMundoX + entidad.hitBox.x;
        entidad.hitBox.y = entidad.posMundoY + entidad.hitBox.y;

        // Obtener la posicion de la hitbox del jugador
        pJuego.jugador.hitBox.x = pJuego.jugador.posMundoX + pJuego.jugador.hitBox.x;
        pJuego.jugador.hitBox.y = pJuego.jugador.posMundoY + pJuego.jugador.hitBox.y;

        switch (entidad.direccion) {
            case ARRIBA -> {
                entidad.hitBox.y -= entidad.velocidad;
                if (entidad.hitBox.intersects(pJuego.jugador.hitBox)) {
                    entidad.hayColision = true;
                }
            }
            case ABAJO -> {
                entidad.hitBox.y += entidad.velocidad;
                if (entidad.hitBox.intersects(pJuego.jugador.hitBox))
                {
                    entidad.hayColision = true;
                }
            }
            case IZQUIERDA -> {
                entidad.hitBox.x -= entidad.velocidad;
                if (entidad.hitBox.intersects(pJuego.jugador.hitBox))
                {
                    entidad.hayColision = true;
                }
            }
            case DERECHA -> {
                entidad.hitBox.x += entidad.velocidad;
                if (entidad.hitBox.intersects(pJuego.jugador.hitBox))
                {
                    entidad.hayColision = true;
                }
            }
        }
        entidad.hitBox.x = entidad.hitbox_XPorDefecto;
        entidad.hitBox.y = entidad.hitbox_YPorDefecto;
        pJuego.jugador.hitBox.x = pJuego.jugador.hitbox_XPorDefecto;
        pJuego.jugador.hitBox.y = pJuego.jugador.hitbox_YPorDefecto;
    }
}
