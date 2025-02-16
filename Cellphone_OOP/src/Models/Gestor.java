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

    private Gestor(){
        this.llamadas = new ArrayList<>();
        this.reuniones = new ArrayList<>();
        this.contactos = new ArrayList<>();
        this.gruposAmigos = new ArrayList<>();
    };

    public static Gestor getInstance(){
        if(instance==null){
            instance = new Gestor();
        }
        return instance;
    }

    public void agendarContacto() {
        input = new Scanner(System.in);
        System.out.println("Va a agendar un contacto. A continuación, elija una opción:");
        System.out.println("1. Familiar");
        System.out.println("2. Amigo");

        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("Ingrese una opción: ");
                opcion = input.nextInt();
                input.nextLine(); // Consumir el salto de línea

                if (opcion == 1 || opcion == 2) {
                    opcionValida = true;
                } else {
                    System.out.println("⚠️ Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
                input.nextLine(); // Limpiar buffer
            }
        }

        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = input.nextLine();
        System.out.println("Ingrese el numero del contacto: ");
        int numeroContacto = input.nextInt();
        input.nextLine();

        Contacto nuevoContacto;

        if (opcion == 1) { // Crear Familiar
            System.out.print("Ingrese el tipo de relación (Ejemplo: hermano, madre, primo, etc.): ");
            String tipoRelacion = input.nextLine();
            nuevoContacto = new Familiar(nombre, numeroContacto, tipoRelacion);
        } else { // Crear Amigo
            System.out.print("Ingrese el apodo del amigo: ");
            String apodo = input.nextLine();

            GrupoAmigos grupoSeleccionado = null;

            if (!gruposAmigos.isEmpty()) {
                System.out.println("¿Desea agregarlo a un grupo existente?");
                System.out.println("1. Sí");
                System.out.println("2. No, crear un nuevo grupo");
                System.out.println("3. No agregar a ningún grupo");

                int opcionGrupo = 0;
                boolean opcionGrupoValida = false;

                while (!opcionGrupoValida) {
                    try {
                        System.out.print("Ingrese una opción: ");
                        opcionGrupo = input.nextInt();
                        input.nextLine(); // Consumir el salto de línea

                        if (opcionGrupo >= 1 && opcionGrupo <= 3) {
                            opcionGrupoValida = true;
                        } else {
                            System.out.println("⚠️ Opción no válida. Intente nuevamente.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
                        input.nextLine(); // Limpiar buffer
                    }
                }

                if (opcionGrupo == 1) { // Agregar a un grupo existente
                    System.out.println("Seleccione un grupo:");

                    for (int i = 0; i < gruposAmigos.size(); i++) {
                        System.out.println((i + 1) + ". " + gruposAmigos.get(i).getNombreGrupo());
                    }

                    int grupoIndex = -1;
                    boolean grupoValido = false;

                    while (!grupoValido) {
                        try {
                            System.out.print("Ingrese el número del grupo: ");
                            grupoIndex = input.nextInt() - 1;
                            input.nextLine(); // Consumir el salto de línea

                            if (grupoIndex >= 0 && grupoIndex < gruposAmigos.size()) {
                                grupoSeleccionado = gruposAmigos.get(grupoIndex);
                                grupoValido = true;
                            } else {
                                System.out.println("⚠️ Número de grupo inválido. Intente nuevamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
                            input.nextLine(); // Limpiar buffer
                        }
                    }
                } else if (opcionGrupo == 2) { // Crear un nuevo grupo
                    System.out.print("Ingrese el nombre del nuevo grupo: ");
                    String nombreGrupo = input.nextLine();
                    grupoSeleccionado = new GrupoAmigos(nombreGrupo, new ArrayList<>());
                    gruposAmigos.add(grupoSeleccionado);
                }
            }

            nuevoContacto = new Amigo(nombre, numeroContacto, apodo, grupoSeleccionado);

            if (grupoSeleccionado != null) {
                grupoSeleccionado.getMiembros().add((Amigo) nuevoContacto);
                System.out.println("✅ Amigo agregado al grupo: " + grupoSeleccionado.getNombreGrupo());
            }
        }

        contactos.add(nuevoContacto);
        System.out.println("✅ Contacto agregado con éxito: " + nuevoContacto.getNombreContacto() + ", Telefono: " + nuevoContacto.getNumeroContacto());
    }


}
