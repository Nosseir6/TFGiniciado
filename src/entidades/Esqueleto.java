package entidades;

import interfaces.Direcciones;
import main.PanelJuego;

import java.awt.*;
import java.util.Random;

public class Esqueleto extends Entidad
{
    public String nombre;


    public Esqueleto(PanelJuego pJuego)
    {
        super(pJuego);
        nombre = "slime";
        velocidad = 1;
        vidaMax = 4;
        vida = vidaMax;

        hitBox = new Rectangle(15,4,24,52);
        direccion = Direcciones.ABAJO;
        hitbox_XPorDefecto = hitBox.x;
        hitbox_YPorDefecto = hitBox.y;

        getImagen();
    }

    public void getImagen()
    {
        //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ABAJO
        imagenAbajo = setup("/esqueleto/Abajo/Abajo1.png");
        abajo1 = setup("/esqueleto/Abajo/Abajo1.png");
        abajo2 = setup("/esqueleto/Abajo/Abajo2.png");
        abajo3 = setup("/esqueleto/Abajo/Abajo3.png");
        abajo4 = setup("/esqueleto/Abajo/Abajo1.png");
        abajo5 = setup("/esqueleto/Abajo/Abajo4.png");
        abajo6 = setup("/esqueleto/Abajo/Abajo5.png");

        //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ARRIBA
        imagenArriba = setup("/esqueleto/Arriba/Arriba1.png");
        arriba1=setup("/esqueleto/Arriba/Arriba1.png");
        arriba2=setup("/esqueleto/Arriba/Arriba2.png");
        arriba3=setup("/esqueleto/Arriba/Arriba3.png");
        arriba4=setup("/esqueleto/Arriba/Arriba1.png");
        arriba5=setup("/esqueleto/Arriba/Arriba4.png");
        arriba6=setup("/esqueleto/Arriba/Arriba5.png");

        //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA LA DERECHA
        imagenDrch = setup("/esqueleto/Derecha/Derecha1.png");
        drch1 = setup("/esqueleto/Derecha/Derecha1.png");
        drch2 = setup("/esqueleto/Derecha/Derecha2.png");
        drch3 = setup("/esqueleto/Derecha/Derecha3.png");
        drch4 = setup("/esqueleto/Derecha/Derecha1.png");
        drch5 = setup("/esqueleto/Derecha/Derecha4.png");
        drch6 = setup("/esqueleto/Derecha/Derecha5.png");

        //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA LA IZQUIERDA
        imagenIzq = setup("/esqueleto/Izquierda/Izquierda1.png");
        izq1 = setup("/esqueleto/Izquierda/Izquierda1.png");
        izq2 = setup("/esqueleto/Izquierda/Izquierda2.png");
        izq3 = setup("/esqueleto/Izquierda/Izquierda3.png");
        izq4 = setup("/esqueleto/Izquierda/Izquierda1.png");
        izq5 = setup("/esqueleto/Izquierda/Izquierda4.png");
        izq6 = setup("/esqueleto/Izquierda/Izquierda5.png");
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
            contadorAcciones = 0;
        }
    }
}
