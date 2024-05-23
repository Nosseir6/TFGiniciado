package objetos;

import interfaces.TipoObjetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cofre extends SuperObjeto
{
    public boolean abierto;
    public Cofre()
    {
        nombre = TipoObjetos.COFRE;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/cofreNuevo.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        colision = true;
        abierto = false;
    }
}
