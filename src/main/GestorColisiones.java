package main;

import entidades.Entidad;

public class GestorColisiones
{
    private PanelJuego pJuego;

    GestorColisiones (PanelJuego panelJuego)
    {
        this.pJuego = panelJuego;
    }

    public void comprobarCasilla(Entidad entidad)
    {
        int entidadXIzqMundo = entidad.posMundoX + entidad.hitBox.x;
        int entidadXDerchMundo = entidad.posMundoX + entidad.hitBox.x + entidad.hitBox.width;
        int entidadYTopMundo = entidad.posMundoY +entidad.hitBox.y;
        int entidadYAbajoMundo = entidad.posMundoY +entidad.hitBox.y + entidad.hitBox.height;

        int entidadColIzq = entidadXIzqMundo/pJuego.dimensionCasillas;
        int entidadColDerch = entidadXDerchMundo/pJuego.dimensionCasillas;
        int entidadTopFila = entidadYTopMundo/pJuego.dimensionCasillas;
        int entidadBotFila = entidadYAbajoMundo/pJuego.dimensionCasillas;

        int casilla1, casilla2;

        switch (entidad.direccion)
        {
            case ARRIBA ->
            {
                entidadTopFila = (entidadYTopMundo - entidad.velocidad)/ pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadTopFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadTopFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision)
                {
                    entidad.hayColision = true;
                }
            }
            case ABAJO ->
            {
                entidadBotFila = (entidadYAbajoMundo + entidad.velocidad)/ pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadBotFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadBotFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision)
                {
                    entidad.hayColision = true;
                }
            }
            case IZQUIERDA ->
            {
                entidadColIzq = (entidadXIzqMundo - entidad.velocidad)/ pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadTopFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColIzq][entidadBotFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision)
                {
                    entidad.hayColision = true;
                }
            }
            case DERECHA ->
            {
                entidadColDerch = (entidadXDerchMundo + entidad.velocidad)/ pJuego.dimensionCasillas;
                casilla1 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadTopFila];
                casilla2 = pJuego.gestCasillas.mapaJuego[entidadColDerch][entidadBotFila];
                if (pJuego.gestCasillas.casillas[casilla1].colision || pJuego.gestCasillas.casillas[casilla2].colision)
                {
                    entidad.hayColision = true;
                }
            }
        }
    }


    public int colisionObjetos(Entidad entidad, boolean jugador)
    {
        int index = 999;

        for (int i = 0; i < pJuego.objetos.length; i++)
        {
            if (pJuego.objetos[i] !=null)
            {
                //Obtener la posicion de la hitbox de la entidad
                entidad.hitBox.x = entidad.posMundoX + entidad.hitBox.x;
                entidad.hitBox.y = entidad.posMundoY + entidad.hitBox.y;

                //Obtener la posion de la hitbox del objeto
                pJuego.objetos[i].hitbox.x = pJuego.objetos[i].posMundoX + pJuego.objetos[i].hitbox.x;
                pJuego.objetos[i].hitbox.y = pJuego.objetos[i].posMundoY + pJuego.objetos[i].hitbox.y;

                switch (entidad.direccion)
                {
                    case ARRIBA ->entidad.hitBox.y -= entidad.velocidad;
                    case ABAJO ->entidad.hitBox.y += entidad.velocidad;
                    case IZQUIERDA ->entidad.hitBox.x -= entidad.velocidad;
                    case DERECHA ->entidad.hitBox.x += entidad.velocidad;
                }
                if (entidad.hitBox.intersects(pJuego.objetos[i].hitbox))
                {
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

    //COLISION CON LOS MONSTRUOS
    public int comprobarEntidad(Entidad entidad, Entidad[] objetivo)
    {
        int index = 999;

        for (int i = 0; i < objetivo.length; i++)
        {
            if (objetivo[i] !=null)
            {
                //Obtener la posicion de la hitbox de la entidad
                entidad.hitBox.x = entidad.posMundoX + entidad.hitBox.x;
                entidad.hitBox.y = entidad.posMundoY + entidad.hitBox.y;

                //Obtener la posion de la hitbox del objeto
                objetivo[i].hitBox.x = objetivo[i].posMundoX + objetivo[i].hitBox.x;
                objetivo[i].hitBox.y = objetivo[i].posMundoY + objetivo[i].hitBox.y;

                switch (entidad.direccion)
                {
                    case ARRIBA -> entidad.hitBox.y -= entidad.velocidad;
                    case ABAJO -> entidad.hitBox.y += entidad.velocidad;
                    case IZQUIERDA -> entidad.hitBox.x -= entidad.velocidad;
                    case DERECHA -> entidad.hitBox.x += entidad.velocidad;
                }
                if (entidad.hitBox.intersects(objetivo[i].hitBox))
                {
                    if (objetivo[i] != entidad)
                    {
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

    public void comprobarJugador(Entidad entidad)
    {
        //Obtener la posicion de la hitbox de la entidad
        entidad.hitBox.x = entidad.posMundoX + entidad.hitBox.x;
        entidad.hitBox.y = entidad.posMundoY + entidad.hitBox.y;

        //Obtener la posion de la hitbox del objeto
        pJuego.jugador.hitBox.x = pJuego.jugador.posMundoX + pJuego.jugador.hitBox.x;
        pJuego.jugador.hitBox.y = pJuego.jugador.posMundoY + pJuego.jugador.hitBox.y;

        switch (entidad.direccion)
        {
            case ARRIBA -> {
                entidad.hitBox.y -= entidad.velocidad;
                if (entidad.hitBox.intersects(pJuego.jugador.hitBox))
                {
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
