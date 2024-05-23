package main;

import entidades.SuperEntidad;

public class GestorColisiones
{
    private PanelJuego pJuego;

    GestorColisiones (PanelJuego panelJuego)
    {
        this.pJuego = panelJuego;
    }

    public void comprobarCasilla(SuperEntidad entidad)
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


    public int colisionObjetos(SuperEntidad entidad, boolean jugador)
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
                    case ARRIBA -> {
                        entidad.hitBox.y -= entidad.velocidad;
                        if (entidad.hitBox.intersects(pJuego.objetos[i].hitbox))
                        {
                            if (pJuego.objetos[i].colision)
                                entidad.hayColision = true;
                            if (jugador)
                                index = i;
                        }
                    }
                    case ABAJO -> {
                        entidad.hitBox.y += entidad.velocidad;
                        if (entidad.hitBox.intersects(pJuego.objetos[i].hitbox))
                        {
                            if (pJuego.objetos[i].colision)
                                entidad.hayColision = true;
                            if (jugador)
                                index = i;
                        }
                    }
                    case IZQUIERDA -> {
                        entidad.hitBox.x -= entidad.velocidad;
                        if (entidad.hitBox.intersects(pJuego.objetos[i].hitbox))
                        {
                            if (pJuego.objetos[i].colision)
                                entidad.hayColision = true;
                            if (jugador)
                                index = i;
                        }
                    }
                    case DERECHA -> {
                        entidad.hitBox.x += entidad.velocidad;
                        if (entidad.hitBox.intersects(pJuego.objetos[i].hitbox))
                        {
                            if (pJuego.objetos[i].colision)
                                entidad.hayColision = true;
                            if (jugador)
                                index = i;
                        }
                    }
                }
                entidad.hitBox.x = entidad.hitbox_XPorDefecto;
                entidad.hitBox.y = entidad.hitbox_YPorDefecto;
                pJuego.objetos[i].hitbox.x = pJuego.objetos[i].hitBoxDefaultX;
                pJuego.objetos[i].hitbox.y = pJuego.objetos[i].hitBoxDefaultY;
            }
        }

        return index;
    }
}
