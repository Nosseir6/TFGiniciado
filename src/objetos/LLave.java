package objetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LLave extends SuperObjeto
{
    public LLave()
    {
        nombre = "Cofre";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/llave.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
