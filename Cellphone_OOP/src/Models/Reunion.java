package Models;

import java.util.Date;

public class Reunion extends Evento{
    private TipoSala sala;

    //Constructor de la clase Reunion
    public Reunion(int idEvento, Date fecha, int duracion, int cantidadParticipantes, String motivo) {
        super(idEvento, fecha, duracion, cantidadParticipantes, motivo);
        gestionarSala(); // Asigna el tipo de sala al crear la reunión
    }

    // Metodo para asignar sala segun cantidad de participantes
    public void gestionarSala() {
        if (getCantidadParticipantes() <=5) {
            sala = TipoSala.CHICA;
        } else if (getCantidadParticipantes() <=15) {
            sala = TipoSala.MEDIANA;
        } else {
            sala = TipoSala.GRANDE;
        }
    }

    //getters y setters de los atributos de la clase Reunion
    public TipoSala getSala() {
        return sala;
    }

    public void setSala(TipoSala sala) {
        this.sala = sala;
    }
}
