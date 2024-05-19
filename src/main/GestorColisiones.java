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
}
