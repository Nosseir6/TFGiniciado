package main;

import objetos.Cofre;
import objetos.LLave;

public class GestorObjetos
{
    PanelJuego pJuego;
    public GestorObjetos(PanelJuego pJuego)
    {
        this.pJuego = pJuego;
    }

    public void setObjeto()
    {
        pJuego.objetos[0] = new Cofre();
        pJuego.objetos[0].xMundo = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[0].yMundo = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[1] = new Cofre();
        pJuego.objetos[1].xMundo = 2 * pJuego.dimensionCasillas;
        pJuego.objetos[1].yMundo = 12 * pJuego.dimensionCasillas;
        pJuego.objetos[2] = new LLave();
        pJuego.objetos[2].xMundo =  pJuego.dimensionCasillas;
        pJuego.objetos[2].yMundo = 15 * pJuego.dimensionCasillas;

    }
}
