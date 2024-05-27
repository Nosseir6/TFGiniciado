package entidades;

import interfaces.Direcciones;
import interfaces.TipoObjetos;
import main.GestorTeclado;
import main.PanelJuego;
import objetos.Cofre;
import objetos.Puerta;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends Entidad
{

    GestorTeclado gestTec;

    public final int posXPantalla;
    public final int posYPantalla;

    public int numLlaves = 0;

    public int vidaMax;
    public int vida;

    public Jugador (PanelJuego pJuego, GestorTeclado gestTec)
    {
        super(pJuego);
        this.gestTec = gestTec;

        posXPantalla = pJuego.anchoPantalla/2 - (pJuego.dimensionCasillas/2);
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
        posMundoX = 607;
        posMundoY = 450;
        velocidad = 3;
        direccion = Direcciones.ABAJO; //ASIGNACION DE VALOR POR DEFECTO PARA SABER COMO SE VA APARECER DIBUAJADO EL PERSONAJE LA PRIMERA VEZ

        vidaMax = 6;
        vida = vidaMax;
    }

    public void setJugadorImagen()
    {

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ABAJO
            imagenAbajo = setup("/caballero/Abajo/Caballero1.png");
            abajo1 = setup("/caballero/Abajo/Caballero2.png");
            abajo2 = setup("/caballero/Abajo/Caballero3.png");
            abajo3 = setup("/caballero/Abajo/Caballero4.png");
            abajo4 = setup("/caballero/Abajo/Caballero5.png");
            abajo5 = setup("/caballero/Abajo/Caballero6.png");
            abajo6 = setup("/caballero/Abajo/Caballero7.png");

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA ARRIBA
            imagenArriba = setup("/caballero/Arriba/Arriba1.png");
            arriba1=setup("/caballero/Arriba/Arriba2.png");
            arriba2=setup("/caballero/Arriba/Arriba3.png");
            arriba3=setup("/caballero/Arriba/Arriba4.png");
            arriba4=setup("/caballero/Arriba/Arriba5.png");
            arriba5=setup("/caballero/Arriba/Arriba6.png");
            arriba6=setup("/caballero/Arriba/Arriba7.png");

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA LA DERECHA
            imagenDrch = setup("/caballero/Derecha/Derecha1.png");
            drch1 = setup("/caballero/Derecha/Derecha2.png");
            drch2 = setup("/caballero/Derecha/Derecha3.png");
            drch3 = setup("/caballero/Derecha/Derecha4.png");
            drch4 = setup("/caballero/Derecha/Derecha5.png");
            drch5 = setup("/caballero/Derecha/Derecha6.png");
            drch6 = setup("/caballero/Derecha/Derecha7.png");

            //ASIGNACION DE LOS SPRITES DE LA ANIMACION DE CORRER HACIA LA IZQUIERDA
            imagenIzq = setup("/caballero/Izquierda/Izquierda1.png");
            izq1 = setup("/caballero/Izquierda/Izquierda2.png");
            izq2 = setup("/caballero/Izquierda/Izquierda3.png");
            izq3 = setup("/caballero/Izquierda/Izquierda4.png");
            izq4 = setup("/caballero/Izquierda/Izquierda5.png");
            izq5 = setup("/caballero/Izquierda/Izquierda6.png");
            izq6 = setup("/caballero/Izquierda/Izquierda7.png");

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

            //COMPROBAR COLISION NPCS
            int indexNPC = pJuego.gestColisiones.comprobarEntidad(this,pJuego.npcs);
            interactuarNPC(indexNPC);

            //COMPROBAR COLISION MONSTRUOS
            int indexMonstruos = pJuego.gestColisiones.comprobarEntidad(this, pJuego.monstruos);
            tocarMonstruo(indexMonstruos);

            //COMPROBAR SI JUGADOR HA MUERTO
            jugadorMuerto();


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

        if (invencibilidad)
        {
            contInven++;
            if (contInven >= 60)
            {
                invencibilidad = false;
                contInven = 0;
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

        if (invencibilidad)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }

        g2.drawImage(imagen, posXPantalla, posYPantalla,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));


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
                    pJuego.iu.mostrarMensaje("Obetenida una llave");
                }
                case COFRE -> {
                    if (((Cofre) pJuego.objetos[i]).abierto)
                    {
                        pJuego.iu.mostrarMensaje("El cofre esta vacio");
                    }
                    else
                    {
                        if (numLlaves > 0)
                        {
                            try {
                                pJuego.objetos[i].imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/cofreAbierto.png"));
                            } catch (IOException e) {
                                System.err.println("No se encontro la imagen del cofre abierto");
                                e.printStackTrace();
                            }
                            numLlaves--;
                            ((Cofre) pJuego.objetos[i]).abierto = true;
                            System.out.println("Tienes " + numLlaves + " llaves");
                        }
                    }
                }
                case PUERTA -> {
                    if (((Puerta) pJuego.objetos[i]).abierta)
                    {
                        pJuego.objetos[i].colision = false;
                    }
                    else
                    {
                        if (numLlaves > 0) {
                            try {
                                pJuego.objetos[i].imagen = ImageIO.read(getClass().getResourceAsStream("/objetos/puertaAbierta.png"));
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.err.println("No se encuentra la imagen de la puerta");
                            }
                            numLlaves--;
                            ((Puerta) pJuego.objetos[i]).abierta = true;
                            pJuego.iu.mostrarMensaje("Puerta abierta");
                        }
                    }


                }
                case ALTAR -> {
                    pJuego.iu.juegoAcabado = true;
                    pJuego.stopMusic();
                    pJuego.playEfectos(1);
                }

            }
        }
    }

    public void interactuarNPC(int index)
    {
        if (index != 999)
        {

        }
    }

    public void tocarMonstruo(int index)
    {
        if (index != 999)
        {
            if (!invencibilidad)
            {
                vida -= 1;
                invencibilidad = true;
            }
        }
    }

    public void jugadorMuerto()
    {
        if (vida == 0)
        {
            pJuego.iu.juegoAcabado = true;
            pJuego.stopMusic();
            pJuego.playEfectos(2);
        }
    }
}
