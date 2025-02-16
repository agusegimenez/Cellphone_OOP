package Models;

public class Amigo extends Contacto {
    private String apodo;
    private GrupoAmigos grupo;

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public GrupoAmigos getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoAmigos grupo) {
        this.grupo = grupo;
    }

    public Amigo(String nombreContacto, int numeroContacto, String apodo, GrupoAmigos grupo) {
        super(nombreContacto, numeroContacto);
        this.apodo = apodo;
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "ID del contacto: " + this.getIdContacto() + "\n" +
                "Nombre: " + this.getNombreContacto() + "\n" +
                "Numero: " + this.getNumeroContacto() + "\n" +
                "Apodo: " + this.getApodo() + "\n" +
                "Pertenece al grupo de Amigos: " + this.getGrupo().getNombreGrupo();
    }
}
