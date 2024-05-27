package main;

import entidades.EsqueletoNPC;
import entidades.Esqueleto;
import objetos.Altar;
import objetos.Cofre;
import objetos.Llave;
import objetos.Puerta;

public class GestorEntidades
{
    PanelJuego pJuego;
    public GestorEntidades(PanelJuego pJuego)
    {
        this.pJuego = pJuego;
    }

    public void setObjeto()
    {
        //COFRES
        pJuego.objetos[0] = new Cofre(pJuego);
        pJuego.objetos[0].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[0].posMundoY = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[1] = new Cofre(pJuego);
        pJuego.objetos[1].posMundoX = 2 * pJuego.dimensionCasillas;
        pJuego.objetos[1].posMundoY = 12 * pJuego.dimensionCasillas;
        //LLAVES
        pJuego.objetos[2] = new Llave(pJuego);
        pJuego.objetos[2].posMundoX = 10 * pJuego.dimensionCasillas;
        pJuego.objetos[2].posMundoY = 15 * pJuego.dimensionCasillas;
        pJuego.objetos[3] = new Llave(pJuego);
        pJuego.objetos[3].posMundoX =  pJuego.dimensionCasillas;
        pJuego.objetos[3].posMundoY = 5 * pJuego.dimensionCasillas;
        pJuego.objetos[4] = new Llave(pJuego);
        pJuego.objetos[4].posMundoX =  8 *pJuego.dimensionCasillas;
        pJuego.objetos[4].posMundoY = 20 * pJuego.dimensionCasillas;
        pJuego.objetos[5] = new Llave(pJuego);
        pJuego.objetos[5].posMundoX =  2 *pJuego.dimensionCasillas;
        pJuego.objetos[5].posMundoY = 27 * pJuego.dimensionCasillas;
        pJuego.objetos[6] = new Llave(pJuego);
        pJuego.objetos[6].posMundoX = 9 *pJuego.dimensionCasillas;
        pJuego.objetos[6].posMundoY = 1 * pJuego.dimensionCasillas;
        pJuego.objetos[7] = new Llave(pJuego);
        pJuego.objetos[7].posMundoX =  3 *pJuego.dimensionCasillas;
        pJuego.objetos[7].posMundoY = 23 * pJuego.dimensionCasillas;
        //PUERTAS
        pJuego.objetos[8] = new Puerta(pJuego);
        pJuego.objetos[8].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[8].posMundoY = 14 * pJuego.dimensionCasillas;
        pJuego.objetos[9] = new Puerta(pJuego);
        pJuego.objetos[9].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[9].posMundoY = 28 * pJuego.dimensionCasillas;
        pJuego.objetos[10] = new Puerta(pJuego);
        pJuego.objetos[10].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[10].posMundoY = 18 * pJuego.dimensionCasillas;
        //ALTAR
        pJuego.objetos[11] = new Altar(pJuego);
        pJuego.objetos[11].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[11].posMundoY = 36 * pJuego.dimensionCasillas;

    }

    public void setNPC()
    {
        pJuego.npcs[0] = new EsqueletoNPC(pJuego);
        pJuego.npcs[0].posMundoX = 17 * pJuego.dimensionCasillas;
        pJuego.npcs[0].posMundoY = 2 * pJuego.dimensionCasillas;
    }

    public void setMonstruo()
    {
        pJuego.monstruos[0] = new Esqueleto(pJuego);
        pJuego.monstruos[0].posMundoX = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[0].posMundoY = 34 * pJuego.dimensionCasillas;
        pJuego.monstruos[1] = new Esqueleto(pJuego);
        pJuego.monstruos[1].posMundoX = 10 * pJuego.dimensionCasillas;
        pJuego.monstruos[1].posMundoY =  34 * pJuego.dimensionCasillas;
        pJuego.monstruos[2] = new Esqueleto(pJuego);
        pJuego.monstruos[2].posMundoX = 3 * pJuego.dimensionCasillas;
        pJuego.monstruos[2].posMundoY =  3 * pJuego.dimensionCasillas;
    }
}
