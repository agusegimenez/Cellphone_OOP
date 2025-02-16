package Models;

import Models.Contacto;

public class Familiar extends Contacto {
    private String tipoRelacion;

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Familiar(String nombreContacto, int numeroContacto, String tipoRelacion) {
        super(nombreContacto, numeroContacto);
        this.tipoRelacion = tipoRelacion;
    }

    @Override
    public String toString() {
        return "ID del contacto: " + this.getIdContacto() + "\n" +
                "Nombre: " + this.getNombreContacto() + "\n" +
                "Numero: " + this.getNumeroContacto() + "\n" +
                "Tipo de relacion: " + this.getTipoRelacion();
    }
}
