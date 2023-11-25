package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Reloj {
    private LocalDateTime horaActual; 
        
    public void actualizarHoraActual() {
        horaActual = LocalDateTime.now();
    }

    public String mostrarHoraActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formatter);
        return "   "+ horaFormateada;
    }
   
}

