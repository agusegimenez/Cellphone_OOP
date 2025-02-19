package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Evento {
    private static int contadorEvento = 1;
    private int idEvento;
    private Date fecha;
    private List<Contacto> participantes;
    private int duracion;
    private int cantidadParticipantes;
    private String motivo;
    private boolean ocurrio=false;

    //Constructor de la clase Evento
    public Evento(Date fecha, int duracion, int cantidadParticipantes, String motivo) {
        this.idEvento = contadorEvento++;
        this.fecha = fecha;
        this.participantes = new ArrayList<>();
        this.duracion = duracion;
        this.cantidadParticipantes = cantidadParticipantes;
        this.motivo = motivo;
    }

    //getters y setters de los atributos de la clase Evento
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Contacto> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Contacto> participantes) {
        this.participantes = participantes;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCantidadParticipantes() {
        return cantidadParticipantes;
    }

    public void setCantidadParticipantes(int cantidadParticipantes) {
        this.cantidadParticipantes = cantidadParticipantes;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isOcurrio() {
        return ocurrio;
    }

    public void setOcurrio(boolean ocurrio) {
        this.ocurrio = ocurrio;
    }

    public abstract String toString();
}
