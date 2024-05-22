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
        pJuego.objetos[0].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[0].posMundoY = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[1] = new Cofre();
        pJuego.objetos[1].posMundoX = 2 * pJuego.dimensionCasillas;
        pJuego.objetos[1].posMundoY = 12 * pJuego.dimensionCasillas;
        pJuego.objetos[2] = new LLave();
        pJuego.objetos[2].posMundoX =  pJuego.dimensionCasillas;
        pJuego.objetos[2].posMundoY = 15 * pJuego.dimensionCasillas;

    }
}
