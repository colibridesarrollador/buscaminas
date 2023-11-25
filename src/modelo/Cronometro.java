package modelo;

import java.time.Duration;
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class Cronometro {

    private LocalTime tiempoInicio;
    private JLabel label;
    private SwingWorker<Void, LocalTime> cronometroWorker;

    public Cronometro(JLabel label) {
        this.label = label;
    }

    public void iniciar() {
        tiempoInicio = LocalTime.now();
        cronometroWorker = new SwingWorker<Void, LocalTime>() {
            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    LocalTime tiempoActual = LocalTime.now();
                    Duration duracion = Duration.between(tiempoInicio, tiempoActual);

                    // Crear un objeto LocalTime con los valores de horas, minutos y segundos
                    LocalTime tiempoTranscurrido = LocalTime.of((int) duracion.toHours(), (int) duracion.toMinutes() % 60, (int) duracion.getSeconds() % 60);

                    // Publicar el tiempo transcurrido para actualizar el JLabel
                    publish(tiempoTranscurrido);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // No es necesario hacer nada en caso de interrupción
                    }
                }
                return null;
            }

            @Override
            protected void process(java.util.List<LocalTime> chunks) {
                LocalTime tiempo = chunks.get(chunks.size() - 1);
                label.setText(String.format("Tiempo: %02d:%02d:%02d", tiempo.getHour(), tiempo.getMinute(), tiempo.getSecond()));
            }
        };
        cronometroWorker.execute();
    }

    public void detener() {
        if (cronometroWorker != null && !cronometroWorker.isDone()) {
            cronometroWorker.cancel(true);
        }
    }
}
