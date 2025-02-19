package Models;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestor {
    private static Gestor instance;
    public List<Llamada> llamadas;
    public List<Reunion> reuniones;
    public List<Contacto> contactos;
    public List<GrupoAmigos> gruposAmigos;
    public Usuario duenio;
    public Scanner input;

    // este constructor inicializa el gestor con listas vacias y es privado para que no se acceda desde fuera de la clase
    private Gestor(){
        this.llamadas = new ArrayList<>();
        this.reuniones = new ArrayList<>();
        try {
            this.contactos = ContactoRepository.cargarContactos();
        } catch (Exception e) {
            System.err.println("⚠️ No se pudieron cargar los contactos: " + e.getMessage());
            this.contactos = new ArrayList<>(); // Si falla, al menos que no sea null
        }
        this.gruposAmigos = new ArrayList<>();
    }


    //singleton, se hace el llamado a la instancia de Gestor, de no existir, la crea
    public static Gestor getInstance(){
        if(instance==null){
            instance = new Gestor();
        }
        return instance;
    }

    private void agendarContacto() {
        input = new Scanner(System.in);
        System.out.println("Va a agendar un contacto. A continuación, elija una opción:");
        System.out.println("1. Familiar");
        System.out.println("2. Amigo");

        int opcion = obtenerOpcion(1, 2);
        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = input.nextLine();

        int numeroContacto = obtenerNumero();

        Contacto nuevoContacto;

        if (opcion == 1) { // Crear Familiar
            System.out.print("Ingrese el tipo de relación (Ejemplo: hermano, madre, primo, etc.): ");
            String tipoRelacion = input.nextLine();
            nuevoContacto = new Familiar(nombre, numeroContacto, tipoRelacion);
        } else { // Crear Amigo
            nuevoContacto = crearAmigo(nombre, numeroContacto);
        }

        contactos.add(nuevoContacto);
        ContactoRepository.guardarContactos(contactos);
        System.out.println("✅ Contacto agregado con éxito: " + nuevoContacto.getNombreContacto() + ", Teléfono: " + nuevoContacto.getNumeroContacto());
    }


     // metodo para obtener una opcion valida dentro de un rango
    private int obtenerOpcion(int min, int max) {
        int opcion = 0;
        while (true) {
            try {
                System.out.print("Ingrese una opción: ");
                opcion = input.nextInt();
                input.nextLine(); // Consumir salto de línea
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
                input.nextLine();
            }
        }
    }


    // metodo para obtener un numero de contacto valido
    private int obtenerNumero() {
        int numero = 0;
        while (true) {
            try {
                System.out.print("Número: ");
                numero = input.nextInt();
                input.nextLine();
                if (numero > 0) {
                    return numero;
                }
                System.out.println("⚠️ El número debe ser positivo.");
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Ingrese un número válido.");
                input.nextLine();
            }
        }
    }


    //metodo para crear un amigo y agregarlo a un grupo de amigos si el usuario lo desea
    private Amigo crearAmigo(String nombre, int numeroContacto) {
        System.out.print("Ingrese el apodo del amigo: ");
        String apodo = input.nextLine();

        GrupoAmigos grupoSeleccionado = null;

        System.out.println("¿Desea agregarlo a un grupo?");
        System.out.println("1. Sí, agregar a un grupo existente");
        System.out.println("2. No, crear un nuevo grupo");
        System.out.println("3. No agregar a ningún grupo");

        int opcionGrupo = obtenerOpcion(1, 3);

        if (opcionGrupo == 1) { // Agregar a grupo existente
            if (gruposAmigos.isEmpty()) {
                System.out.println("No tiene grupos agregados. Debe crear uno nuevo.");
                grupoSeleccionado = crearNuevoGrupo();
            } else {
                grupoSeleccionado = seleccionarGrupoExistente();
            }
        } else if (opcionGrupo == 2) { // Crear un nuevo grupo
            grupoSeleccionado = crearNuevoGrupo();
        }

        Amigo nuevoAmigo = new Amigo(nombre, numeroContacto, apodo, grupoSeleccionado);

        if (grupoSeleccionado != null) {
            grupoSeleccionado.getMiembros().add(nuevoAmigo);
            System.out.println("✅ Amigo agregado al grupo: " + grupoSeleccionado.getNombreGrupo());
        }

        return nuevoAmigo;
    }

    //metodo para agregar un grupo de amigos
    private GrupoAmigos crearNuevoGrupo() {
        System.out.print("Ingrese el nombre del nuevo grupo: ");
        String nombreGrupo = input.nextLine();
        GrupoAmigos nuevoGrupo = new GrupoAmigos(nombreGrupo, new ArrayList<>());
        gruposAmigos.add(nuevoGrupo);
        return nuevoGrupo;
    }


    //metodo para seleccionar un grupo existente
    private GrupoAmigos seleccionarGrupoExistente() {
        System.out.println("Seleccione un grupo:");
        for (int i = 0; i < gruposAmigos.size(); i++) {
            System.out.println((i + 1) + ". " + gruposAmigos.get(i).getNombreGrupo());
        }

        int grupoIndex = obtenerOpcion(1, gruposAmigos.size()) - 1;
        return gruposAmigos.get(grupoIndex);
    }


    //metodo para mostrar los contactos
    private void mostrarContactos(){
        for(Contacto contacto : contactos){
            String info = contacto.toString();
            System.out.println(info);
            System.out.println("");
        }
    }


    //metodo para buscar un contacto
    private Contacto buscarContacto(int idContacto) {
        for (Contacto contacto : contactos) {
            if (idContacto == contacto.getIdContacto()) {
                System.out.println("✅ Contacto encontrado!");
                return contacto;
            }
        }
        System.out.println("⚠️ El contacto no fue encontrado.");
        return null;
    }

    //metodo para eliminar un contacto
    private void eliminarContacto(int idContacto){
        boolean eliminado = contactos.removeIf(contacto -> contacto.getIdContacto() == idContacto);
        if (eliminado) {
            System.out.println("✅ El contacto ha sido removido exitosamente!");
        } else {
            System.out.println("⚠️ El contacto no fue encontrado.");
        }
    }

    private Usuario crearDuenio(){
        System.out.println("Bienvenido/a! Para hacer uso del celular por primera vez tiene que registrarse.");
        System.out.println("Ingrese su nombre: ");
        String nombreUsuario = input.next();
        int numero = obtenerNumero();
        Usuario usuario = new Usuario(getInstance(), nombreUsuario, numero);

        return usuario;
    }


    public void mostrarReuniones(){
        for(Reunion reunion : reuniones){
            String info = reunion.toString();
            System.out.println(info);
            System.out.println("");
        }
    }

    public void mostrarLlamadas(){
        for(Llamada llamada : llamadas){
            String info = llamada.toString();
            System.out.println(info);
            System.out.println("");
        }
    }

}
