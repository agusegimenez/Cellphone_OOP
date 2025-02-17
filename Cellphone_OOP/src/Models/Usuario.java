package Models;

public class Usuario {

    public Gestor gestor;
    private String nombre;
    private int  numeroCelular;

    //Constructor de la clase Usuario
    public Usuario(Gestor gestor, String nombre, int numeroCelular) {
        this.gestor = gestor;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
    }

    //getters y setters de los atributos de la clase Usuario
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumeroCelular() {
        return numeroCelular;
    }
    public void setNumeroCelular(int numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
}
