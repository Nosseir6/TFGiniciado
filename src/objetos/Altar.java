package objetos;

import interfaces.TipoObjetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Altar extends SuperObjeto
{
    public boolean abierto;
    public Altar()
    {
        nombre = TipoObjetos.ALTAR;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/altarFinal.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        colision = true;
        abierto = false;
    }
}
