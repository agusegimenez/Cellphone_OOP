package Models;

import java.util.Date;

public class Llamada extends Evento{
    private boolean esVideollamada = false;

    //Constructor de la clase Llamada
    public Llamada(int idEvento, Date fecha, int duracion, int cantidadParticipantes, String motivo) {
        super(idEvento, fecha, duracion, cantidadParticipantes, motivo);
    }

    //getters y setters de los atributos de la clase Llamada
    public boolean isEsVideollamada() {
        return esVideollamada;
    }

    public void setEsVideollamada(boolean esVideollamada) {
        this.esVideollamada = esVideollamada;
    }
}
