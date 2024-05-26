package main;

import objetos.Cofre;
import objetos.Llave;
import objetos.Puerta;

public class GestorObjetos
{
    PanelJuego pJuego;
    public GestorObjetos(PanelJuego pJuego)
    {
        this.pJuego = pJuego;
    }

    public void setObjeto()
    {
        //COFRES
        pJuego.objetos[0] = new Cofre();
        pJuego.objetos[0].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[0].posMundoY = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[1] = new Cofre();
        pJuego.objetos[1].posMundoX = 2 * pJuego.dimensionCasillas;
        pJuego.objetos[1].posMundoY = 12 * pJuego.dimensionCasillas;
        //LLAVES
        pJuego.objetos[2] = new Llave();
        pJuego.objetos[2].posMundoX = 10 * pJuego.dimensionCasillas;
        pJuego.objetos[2].posMundoY = 15 * pJuego.dimensionCasillas;
        pJuego.objetos[3] = new Llave();
        pJuego.objetos[3].posMundoX =  pJuego.dimensionCasillas;
        pJuego.objetos[3].posMundoY = 5 * pJuego.dimensionCasillas;
        pJuego.objetos[4] = new Llave();
        pJuego.objetos[4].posMundoX =  8 *pJuego.dimensionCasillas;
        pJuego.objetos[4].posMundoY = 20 * pJuego.dimensionCasillas;
        pJuego.objetos[5] = new Llave();
        pJuego.objetos[5].posMundoX =  2 *pJuego.dimensionCasillas;
        pJuego.objetos[5].posMundoY = 27 * pJuego.dimensionCasillas;
        pJuego.objetos[6] = new Llave();
        pJuego.objetos[6].posMundoX = 9 *pJuego.dimensionCasillas;
        pJuego.objetos[6].posMundoY = 1 * pJuego.dimensionCasillas;
        pJuego.objetos[7] = new Llave();
        pJuego.objetos[7].posMundoX =  3 *pJuego.dimensionCasillas;
        pJuego.objetos[7].posMundoY = 23 * pJuego.dimensionCasillas;
        //PUERTAS
        pJuego.objetos[8] = new Puerta();
        pJuego.objetos[8].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[8].posMundoY = 14 * pJuego.dimensionCasillas;
        pJuego.objetos[9] = new Puerta();
        pJuego.objetos[9].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[9].posMundoY = 28 * pJuego.dimensionCasillas;
        pJuego.objetos[10] = new Puerta();
        pJuego.objetos[10].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[10].posMundoY = 18 * pJuego.dimensionCasillas;

    }
}
