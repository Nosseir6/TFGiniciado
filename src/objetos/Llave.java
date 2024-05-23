package objetos;

import interfaces.TipoObjetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Llave extends SuperObjeto
{
    public Llave()
    {
        nombre = TipoObjetos.LLAVE;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/llave.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
