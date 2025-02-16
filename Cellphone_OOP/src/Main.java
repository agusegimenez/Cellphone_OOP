import Models.Celular;

public class Main {
    public static void main(String[] args) {
        Celular celular = Celular.getInstance();
        celular.agendarContacto();
    }
}
