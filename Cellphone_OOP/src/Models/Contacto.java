package Models;

public abstract class Contacto {
    private static int contadorId = 1;
    private int idContacto;
    private String nombreContacto;
    private int numeroContacto;

    public int getIdContacto() {
        return idContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public int getNumeroContacto(){
        return numeroContacto;
    }

    public Contacto(String nombreContacto, int numeroContacto) {
        this.idContacto = idContacto++;
        this.nombreContacto = nombreContacto;
        this.numeroContacto = numeroContacto;
    }
}
