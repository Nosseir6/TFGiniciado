package main;

import entidades.EsqueletoNPC;
import entidades.Esqueleto;
import objetos.Altar;
import objetos.Cofre;
import objetos.Llave;
import objetos.Puerta;

/**
 * La clase GestorEntidades se encarga de inicializar y configurar las entidades del juego.
 */
public class GestorEntidades {

    /** El PanelJuego al que pertenecen las entidades. */
    PanelJuego pJuego;

    /**
     * Constructor para la clase GestorEntidades.
     * @param pJuego El PanelJuego al que pertenecen las entidades.
     */
    public GestorEntidades(PanelJuego pJuego) {
        this.pJuego = pJuego;
    }

    /**
     * Inicializa y configura los objetos del juego, como cofres, llaves, puertas y el altar.
     */
    public void setObjeto() {
        // COFRES
        pJuego.objetos[0] = new Cofre(pJuego);
        pJuego.objetos[0].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[0].posMundoY = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[1] = new Cofre(pJuego);
        pJuego.objetos[1].posMundoX = 2 * pJuego.dimensionCasillas;
        pJuego.objetos[1].posMundoY = 12 * pJuego.dimensionCasillas;

        // LLAVES
        pJuego.objetos[3] = new Llave(pJuego);
        pJuego.objetos[3].posMundoX = 15 *pJuego.dimensionCasillas;
        pJuego.objetos[3].posMundoY = 5 * pJuego.dimensionCasillas;
        pJuego.objetos[4] = new Llave(pJuego);
        pJuego.objetos[4].posMundoX = 2 * pJuego.dimensionCasillas;
        pJuego.objetos[4].posMundoY = 27 * pJuego.dimensionCasillas;
        pJuego.objetos[5] = new Llave(pJuego);
        pJuego.objetos[5].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[5].posMundoY = 1 * pJuego.dimensionCasillas;
        pJuego.objetos[6] = new Llave(pJuego);
        pJuego.objetos[6].posMundoX = 3 * pJuego.dimensionCasillas;
        pJuego.objetos[6].posMundoY = 23 * pJuego.dimensionCasillas;

        // PUERTAS
        pJuego.objetos[8] = new Puerta(pJuego);
        pJuego.objetos[8].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[8].posMundoY = 14 * pJuego.dimensionCasillas;
        pJuego.objetos[9] = new Puerta(pJuego);
        pJuego.objetos[9].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[9].posMundoY = 28 * pJuego.dimensionCasillas;
        pJuego.objetos[10] = new Puerta(pJuego);
        pJuego.objetos[10].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.objetos[10].posMundoY = 18 * pJuego.dimensionCasillas;

        // ALTAR
        pJuego.objetos[11] = new Altar(pJuego);
        pJuego.objetos[11].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.objetos[11].posMundoY = 36 * pJuego.dimensionCasillas;
    }

    /**
     * Inicializa y configura el NPC del juego.
     */
    public void setNPC() {

    }

    /**
     * Inicializa y configura los monstruos del juego.
     */
    public void setMonstruo() {
        pJuego.monstruos[0] = new Esqueleto(pJuego);
        pJuego.monstruos[0].posMundoX = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[0].posMundoY = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[1] = new Esqueleto(pJuego);
        pJuego.monstruos[1].posMundoX = 15 * pJuego.dimensionCasillas;
        pJuego.monstruos[1].posMundoY = 7 * pJuego.dimensionCasillas;
        pJuego.monstruos[2] = new Esqueleto(pJuego);
        pJuego.monstruos[2].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.monstruos[2].posMundoY = 8 * pJuego.dimensionCasillas;
        pJuego.monstruos[3] = new Esqueleto(pJuego);
        pJuego.monstruos[3].posMundoX = 18 * pJuego.dimensionCasillas;
        pJuego.monstruos[3].posMundoY = 11 * pJuego.dimensionCasillas;
        pJuego.monstruos[4] = new Esqueleto(pJuego);
        pJuego.monstruos[4].posMundoX = 15 * pJuego.dimensionCasillas;
        pJuego.monstruos[4].posMundoY = 20 * pJuego.dimensionCasillas;
        pJuego.monstruos[5] = new Esqueleto(pJuego);
        pJuego.monstruos[5].posMundoX = 17 * pJuego.dimensionCasillas;
        pJuego.monstruos[5].posMundoY = 22 * pJuego.dimensionCasillas;
        pJuego.monstruos[6] = new Esqueleto(pJuego);
        pJuego.monstruos[6].posMundoX = 12 * pJuego.dimensionCasillas;
        pJuego.monstruos[6].posMundoY = 25 * pJuego.dimensionCasillas;
        pJuego.monstruos[7] = new Esqueleto(pJuego);
        pJuego.monstruos[7].posMundoX = 15 * pJuego.dimensionCasillas;
        pJuego.monstruos[7].posMundoY = 30 * pJuego.dimensionCasillas;
        pJuego.monstruos[8] = new Esqueleto(pJuego);
        pJuego.monstruos[8].posMundoX = 8 * pJuego.dimensionCasillas;
        pJuego.monstruos[8].posMundoY = 32 * pJuego.dimensionCasillas;
        pJuego.monstruos[9] = new Esqueleto(pJuego);
        pJuego.monstruos[9].posMundoX = 15 * pJuego.dimensionCasillas;
        pJuego.monstruos[9].posMundoY = 15 * pJuego.dimensionCasillas;
        pJuego.monstruos[10] = new Esqueleto(pJuego);
        pJuego.monstruos[10].posMundoX = 2 * pJuego.dimensionCasillas;
        pJuego.monstruos[10].posMundoY = 18 * pJuego.dimensionCasillas;
        pJuego.monstruos[11] = new Esqueleto(pJuego);
        pJuego.monstruos[11].posMundoX = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[11].posMundoY = 24 * pJuego.dimensionCasillas;
        pJuego.monstruos[12] = new Esqueleto(pJuego);
        pJuego.monstruos[12].posMundoX = 9 * pJuego.dimensionCasillas;
        pJuego.monstruos[12].posMundoY = 37 * pJuego.dimensionCasillas;
        pJuego.monstruos[13] = new Esqueleto(pJuego);
        pJuego.monstruos[13].posMundoX = 17 * pJuego.dimensionCasillas;
        pJuego.monstruos[13].posMundoY = 25 * pJuego.dimensionCasillas;
        pJuego.monstruos[14] = new Esqueleto(pJuego);
        pJuego.monstruos[14].posMundoX = 10 * pJuego.dimensionCasillas;
        pJuego.monstruos[14].posMundoY = 36 * pJuego.dimensionCasillas;
        pJuego.monstruos[15] = new Esqueleto(pJuego);
        pJuego.monstruos[15].posMundoX = 12 * pJuego.dimensionCasillas;
        pJuego.monstruos[15].posMundoY = 35 * pJuego.dimensionCasillas;
        pJuego.monstruos[16] = new Esqueleto(pJuego);
        pJuego.monstruos[16].posMundoX = 14 * pJuego.dimensionCasillas;
        pJuego.monstruos[16].posMundoY = 16 * pJuego.dimensionCasillas;
        pJuego.monstruos[17] = new Esqueleto(pJuego);
        pJuego.monstruos[17].posMundoX = 18 * pJuego.dimensionCasillas;
        pJuego.monstruos[17].posMundoY = 32 * pJuego.dimensionCasillas;
        pJuego.monstruos[18] = new Esqueleto(pJuego);
        pJuego.monstruos[18].posMundoX = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[18].posMundoY = 25 * pJuego.dimensionCasillas;
        pJuego.monstruos[19] = new Esqueleto(pJuego);
        pJuego.monstruos[19].posMundoX = 11 * pJuego.dimensionCasillas;
        pJuego.monstruos[19].posMundoY = 37 * pJuego.dimensionCasillas;
        pJuego.monstruos[20] = new Esqueleto(pJuego);
        pJuego.monstruos[20].posMundoX = 16 * pJuego.dimensionCasillas;
        pJuego.monstruos[20].posMundoY = 38 * pJuego.dimensionCasillas;
        pJuego.monstruos[21] = new Esqueleto(pJuego);
        pJuego.monstruos[21].posMundoX = 8 * pJuego.dimensionCasillas;
        pJuego.monstruos[21].posMundoY = 33 * pJuego.dimensionCasillas;
        pJuego.monstruos[22] = new Esqueleto(pJuego);
        pJuego.monstruos[22].posMundoX = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[22].posMundoY = 22 * pJuego.dimensionCasillas;
        pJuego.monstruos[23] = new Esqueleto(pJuego);
        pJuego.monstruos[23].posMundoX = 17 * pJuego.dimensionCasillas;
        pJuego.monstruos[23].posMundoY = 26 * pJuego.dimensionCasillas;
        pJuego.monstruos[24] = new Esqueleto(pJuego);
        pJuego.monstruos[24].posMundoX = 4 * pJuego.dimensionCasillas;
        pJuego.monstruos[24].posMundoY = 25 * pJuego.dimensionCasillas;
        pJuego.monstruos[25] = new Esqueleto(pJuego);
        pJuego.monstruos[25].posMundoX = 6 * pJuego.dimensionCasillas;
        pJuego.monstruos[25].posMundoY = 30 * pJuego.dimensionCasillas;
        pJuego.monstruos[26] = new Esqueleto(pJuego);
        pJuego.monstruos[26].posMundoX = 7 * pJuego.dimensionCasillas;
        pJuego.monstruos[26].posMundoY = 25 * pJuego.dimensionCasillas;
        pJuego.monstruos[27] = new Esqueleto(pJuego);
        pJuego.monstruos[27].posMundoX = 3 * pJuego.dimensionCasillas;
        pJuego.monstruos[27].posMundoY = 5 * pJuego.dimensionCasillas;
        pJuego.monstruos[28] = new Esqueleto(pJuego);
        pJuego.monstruos[28].posMundoX = 13 * pJuego.dimensionCasillas;
        pJuego.monstruos[28].posMundoY = 7 * pJuego.dimensionCasillas;
        pJuego.monstruos[29] = new Esqueleto(pJuego);
        pJuego.monstruos[29].posMundoX = 6 * pJuego.dimensionCasillas;
        pJuego.monstruos[29].posMundoY = 35 * pJuego.dimensionCasillas;

    }
}
