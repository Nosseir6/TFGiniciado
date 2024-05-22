package objetos;

import interfaces.TipoObjetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LLave extends SuperObjeto
{
    public LLave()
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
