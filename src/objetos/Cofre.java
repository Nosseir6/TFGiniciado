package objetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cofre extends SuperObjeto
{
    public Cofre()
    {
        nombre = "Cofre";
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/cofre.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
