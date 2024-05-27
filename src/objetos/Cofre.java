package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cofre extends SuperObjeto
{
    public boolean abierto;
    PanelJuego pJuego;

    public Cofre(PanelJuego pJuego)
    {
        nombre = TipoObjetos.COFRE;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/cofreNuevo.png"));
            util.escalada(imagen,pJuego.dimensionCasillas,pJuego.dimensionCasillas);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        colision = true;
        abierto = false;
    }
}
