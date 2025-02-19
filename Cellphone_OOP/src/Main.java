import Models.Gestor;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = Gestor.getInstance();
        gestor.agendarContacto();
        gestor.agendarContacto();
        gestor.mostrarContactos();
        gestor.mostrarReuniones();
        gestor.mostrarLlamadas();
    }
}
