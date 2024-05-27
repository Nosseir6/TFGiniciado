package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Corazon extends SuperObjeto
{
    PanelJuego pJuego;

    public Corazon(PanelJuego pJuego)
    {
        this.pJuego = pJuego;

        nombre = TipoObjetos.CORAZON;

        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/corazon/corazonComp.png"));
            util.escalada(imagen,pJuego.dimensionCasillas,pJuego.dimensionCasillas);
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/objetos/corazon/medioCor.png"));
            util.escalada(imagen2,pJuego.dimensionCasillas,pJuego.dimensionCasillas);
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/objetos/corazon/corazonVacio.png"));
            util.escalada(imagen3,pJuego.dimensionCasillas,pJuego.dimensionCasillas);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
