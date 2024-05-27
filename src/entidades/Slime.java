package entidades;

import interfaces.Direcciones;
import main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Slime extends SuperEntidad
{
    public String nombre;


    public Slime(PanelJuego pJuego)
    {
        super(pJuego);
        nombre = "slime";
        velocidad = 1;
        vidaMax = 4;
        vida = vidaMax;

        hitBox.x = 6;
        hitBox.y = 10;
        hitBox.height = 15;
        hitBox.width = 19;

        hitbox_XPorDefecto = hitBox.x;
        hitbox_YPorDefecto = hitBox.y;

        getImagen();
    }

    public void getImagen()
    {
            arriba1 = setup("/enemigos/slime/Slime.png");
            arriba2 = setup("/enemigos/slime/Slime2.png");
            abajo1 = setup("/enemigos/slime/Slime.png");
            abajo2 = setup("/enemigos/slime/Slime2.png");
            izq1 = setup("/enemigos/slime/Slime.png");
            izq2 = setup("/enemigos/slime/Slime2.png");
            drch1 = setup("/enemigos/slime/Slime.png");
            drch2 = setup("/enemigos/slime/Slime2.png");
    }

    public void setAccion()
    {
        contadorAcciones++;
        if (contadorAcciones == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25)
            {
                direccion = Direcciones.ARRIBA;
            }
            if (i > 25 && i <= 50)
            {
                direccion = Direcciones.ABAJO;
            }
            if (i > 50 && i <= 75)
            {
                direccion = Direcciones.IZQUIERDA;
            }
            if (i > 75 && i <= 100)
            {
                direccion = Direcciones.DERECHA;
            }
        }
    }
}
