package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Llave extends SuperObjeto
{
    PanelJuego pJuego;
    public Llave(PanelJuego pJuego)
    {
        nombre = TipoObjetos.LLAVE;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/llave.png"));
            util.escalada(imagen,pJuego.dimensionCasillas,pJuego.dimensionCasillas);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
