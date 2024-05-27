package sonido;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * La clase Sonido permite la reproducción de archivos de audio en el juego.
 */
public class Sonido {
    /** El clip de audio para reproducir el sonido. */
    Clip clip;

    /** Arreglo de URLs que almacena las ubicaciones de los archivos de sonido. */
    URL[] sonidos = new URL[30];

    /**
     * Constructor de la clase Sonido.
     * Inicializa las ubicaciones de los archivos de sonido.
     */
    public Sonido() {
        sonidos[0] = getClass().getResource("/sonidos/Hades-No-Escape.wav");
        sonidos[1] = getClass().getResource("/sonidos/LevelFinish.wav");
        sonidos[2] = getClass().getResource("/sonidos/Muerte.wav");
    }

    /**
     * Establece el archivo de audio a reproducir.
     * @param i El índice del archivo de sonido en el arreglo de URLs.
     */
    public void setFile(int i) {
        if (i < 0 || i >= sonidos.length || sonidos[i] == null)
        {
            System.err.println("El índice del sonido no es válido o el sonido no está asignado.");
            return;
        }

        try (AudioInputStream ai = AudioSystem.getAudioInputStream(sonidos[i]))
        {
            if (clip != null)
            {
                clip.close();
            }
            clip = AudioSystem.getClip();
            clip.open(ai);
        } catch (UnsupportedAudioFileException e)
        {
            System.err.println("El formato del archivo de audio no es compatible: " + e.getMessage());
        } catch (IOException e)
        {
            System.err.println("Error al leer el archivo de audio: " + e.getMessage());
        } catch (LineUnavailableException e)
        {
            System.err.println("Línea de audio no disponible: " + e.getMessage());
        } catch (Exception e)
        {
            System.err.println("Error desconocido: " + e.getMessage());
        }
    }

    /** Reproduce el sonido. */
    public void play() {
        if (clip != null)
        {
            clip.start();
        }
        else
        {
            System.err.println("El clip no está inicializado.");
        }
    }

    /** Reproduce el sonido en un bucle continuo. */
    public void loop() {
        if (clip != null)
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else
        {
            System.err.println("El clip no está inicializado.");
        }
    }

    /** Detiene la reproducción del sonido. */
    public void stop() {
        if (clip != null)
        {
            clip.stop();
        }
        else
        {
            System.err.println("El clip no está inicializado.");
        }
    }

    /**
     * Establece el volumen del sonido.
     * @param volume El volumen del sonido en un rango de 0.0 a 1.0.
     */
    public void setVolume(float volume) {
        if (clip != null)
        {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
        else
        {
            System.err.println("El clip no está inicializado.");
        }
    }
}
