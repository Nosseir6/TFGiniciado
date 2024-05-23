package entidades;

import interfaces.Direcciones;
import interfaces.TipoObjetos;
import main.GestorTeclado;
import main.PanelJuego;
import objetos.Cofre;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends SuperEntidad
{

    PanelJuego pJuego;
    GestorTeclado gestTec;

    public final int posXPantalla;
    public final int posYPantalla;

    private int numLlaves = 0;
    public Jugador (PanelJuego pJuego, GestorTeclado gestTec)
    {
        this.pJuego = pJuego;
        this.gestTec = gestTec;

        posXPantalla = pJuego.anchoPantalla/2 - (pJuego.dimensionCasillas);
        posYPantalla = pJuego.altoPantalla/2 - (pJuego.dimensionCasillas/2);

        hitBox = new Rectangle();
        hitBox.x = 12 * pJuego.escala;
        hitBox.y = 8 * pJuego.escala;
        hitBox.width = 12 * pJuego.escala;
        hitBox.height = 20 * pJuego.escala;


        hitbox_XPorDefecto = hitBox.x;
        hitbox_YPorDefecto = hitBox.y;

        setValoresPorDefecto();
        setJugadorImagen();
    }

    public void setValoresPorDefecto()
    {
        posMundoX = 500;
        posMundoY = 100;
        velocidad = 3;
        direccion = Direcciones.ABAJO; //ASIGNACION DE VALOR POR DEFECTO PARA SABER COMO SE VA APARECER DIBUAJADO EL PERSONAJE LA PRIMERA VEZ
    }

    public void setJugadorImagen()
    {
        try
        {
            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ABAJO
            imagenAbajo = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero1.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero2.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero3.png"));
            abajo3 = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero4.png"));
            abajo4 = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero5.png"));
            abajo5 = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero6.png"));
            abajo6 = ImageIO.read(getClass().getResourceAsStream("/caballero/Abajo/Caballero7.png"));

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ARRIBA
            imagenArriba = ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba1.png"));
            arriba1=ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba2.png"));
            arriba2=ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba3.png"));
            arriba3=ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba4.png"));
            arriba4=ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba5.png"));
            arriba5=ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba6.png"));
            arriba6=ImageIO.read(getClass().getResourceAsStream("/caballero/Arriba/Arriba7.png"));

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA LA DERECHA
            imagenDrch = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha1.png"));
            drch1 = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha2.png"));
            drch2 = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha3.png"));
            drch3 = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha4.png"));
            drch4 = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha5.png"));
            drch5 = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha6.png"));
            drch6 = ImageIO.read(getClass().getResourceAsStream("/caballero/Derecha/Derecha7.png"));

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA LA IZQUIERDA
            imagenIzq = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda1.png"));
            izq1 = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda2.png"));
            izq2 = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda3.png"));
            izq3 = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda4.png"));
            izq4 = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda5.png"));
            izq5 = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda6.png"));
            izq6 = ImageIO.read(getClass().getResourceAsStream("/caballero/Izquierda/Izquierda7.png"));
        }catch (IOException e)
        {
            System.out.println("Error al acceder a la imagenes");
            e.printStackTrace();
        }


    }

    public void update() {
        if (gestTec.arribaPres || gestTec.abajoPres || gestTec.drchPres || gestTec.izqPres) {
            if (gestTec.arribaPres) {
                direccion = Direcciones.ARRIBA;
            } else if (gestTec.izqPres) {
                direccion = Direcciones.IZQUIERDA;
            } else if (gestTec.drchPres) {
                direccion = Direcciones.DERECHA;
            } else if (gestTec.abajoPres) {
                direccion = Direcciones.ABAJO;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 7;
                spriteCounter = 0;
            }


            //COMPROBAR COLISION DE CASILLAS
            hayColision = false;
            pJuego.gestColisiones.comprobarCasilla(this);

            //COMPROBAR COLISION DE OBJETOS
            int indexObjeto = pJuego.gestColisiones.colisionObjetos(this,true);
            interactuarConObjetos(indexObjeto);

            //SI hayColision == false EL JUGADOR SE PUEDE MOVER
            if (!hayColision) {
                switch (direccion) {
                    case ARRIBA -> posMundoY -= velocidad; // Multiplicar por tiempoDelta
                    case ABAJO -> posMundoY += velocidad; // Multiplicar por tiempoDelta
                    case DERECHA -> posMundoX += velocidad; // Multiplicar por tiempoDelta
                    case IZQUIERDA -> posMundoX -= velocidad; // Multiplicar por tiempoDelta
                }
            } else {
                spriteNum = 0;
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2)
    {
        BufferedImage[] imagenesArriba = {imagenArriba, arriba1, arriba2, arriba3, arriba4, arriba5, arriba6};
        BufferedImage[] imagenesAbajo = {imagenAbajo, abajo1, abajo2, abajo3, abajo4, abajo5, abajo6};
        BufferedImage[] imagenesIzquierda = {imagenIzq, izq1, izq2, izq3, izq4, izq5, izq6};
        BufferedImage[] imagenesDerecha = {imagenDrch, drch1, drch2, drch3, drch4, drch5, drch6};

        BufferedImage imagen = null;

        switch (direccion)
        {
            case ABAJO -> imagen = imagenesAbajo[spriteNum];
            case ARRIBA -> imagen = imagenesArriba[spriteNum];
            case IZQUIERDA -> imagen = imagenesIzquierda[spriteNum];
            case DERECHA -> imagen = imagenesDerecha[spriteNum];
        }

        g2.drawImage(imagen, posXPantalla, posYPantalla,pJuego.dimensionCasillas,pJuego.dimensionCasillas, null);
    }

    public void interactuarConObjetos(int i)
    {
        if (i != 999)
        {
            TipoObjetos nombreObjeto = pJuego.objetos[i].nombre;

            switch (nombreObjeto)
            {
                case LLAVE -> {
                    pJuego.objetos[i] = null;
                    numLlaves++;
                    System.out.println("Tienes "+numLlaves+" llaves");
                }
                case COFRE -> {
                    if (pJuego.objetos[i] instanceof Cofre)
                    {
                        if (((Cofre) pJuego.objetos[i]).abierto)
                        {
                            System.out.println("El cofre esta vacio");
                        }
                        else
                        {
                            if (numLlaves > 0)
                            {
                                try {
                                    pJuego.objetos[i].imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/cofreAbierto.png"));
                                }catch (IOException e)
                                {
                                    System.out.println("No se encontro la imagen del cofre abierto");
                                    e.printStackTrace();
                                }
                                numLlaves--;
                                ((Cofre) pJuego.objetos[i]).abierto = true;
                                System.out.println("Tienes "+numLlaves+" llaves");
                            }
                        }
                    }
                }
                case PUERTA -> {
                    if (numLlaves > 0 )
                    {
                        try {
                            pJuego.objetos[i].imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/puerta_Abierta.png"));
                        }catch (IOException e)
                        {
                            System.out.println("Imagen de la puerta no encontrada");
                            e.printStackTrace();
                        }
                        pJuego.objetos[i].colision = false;
                        numLlaves--;
                        System.out.println("Tienes "+numLlaves+" llaves");

                    }
                }
            }
        }
    }
}
