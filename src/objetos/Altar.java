package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Altar extends SuperObjeto
{
    public boolean abierto;
    PanelJuego pJuego;

    public Altar(PanelJuego pJuego)
    {
        nombre = TipoObjetos.ALTAR;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/altarFinal.png"));
            util.escalada(imagen,pJuego.dimensionCasillas,pJuego.dimensionCasillas);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        colision = true;
        abierto = false;
    }
}
