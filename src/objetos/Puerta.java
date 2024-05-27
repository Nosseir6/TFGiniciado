package objetos;

import interfaces.TipoObjetos;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Puerta extends SuperObjeto
{
    public boolean abierta;
    PanelJuego pJuego;
    public Puerta(PanelJuego pJuego)
    {
        nombre = TipoObjetos.PUERTA;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/puerta.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("No se ha encontrado la imagen de la puerta");
        }
        colision = true;
        abierta = false;
    }

}
