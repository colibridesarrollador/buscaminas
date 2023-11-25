package modelo;


import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Musica {
    private Clip clip;
    private boolean estaReproduciendo; // Nuevo atributo para indicar si la música está reproduciéndose

    public Musica(String rutaMusica) {
        try {
            // Obtiene la URL del archivo de música desde el paquete "musica"
            URL urlMusica = getClass().getResource("/musica/" + rutaMusica);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(urlMusica);

            DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
            clip = (Clip) AudioSystem.getLine(info);

            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void reproducir() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
            estaReproduciendo = true; // Actualiza el estado de reproducción
        }
    }

    public void parar() {
        if (clip != null) {
            clip.stop();
            clip.close();
            estaReproduciendo = false;
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            estaReproduciendo = false;
        }
    }
    
    public boolean estaActivada() {
        return estaReproduciendo;//devuelve si está activa o no la musica
    }
}
