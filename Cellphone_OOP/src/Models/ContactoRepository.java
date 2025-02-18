package Models;

import java.io.*;
import java.util.List;

public class ContactoRepository {

    private static final String FILE_NAME = "contactos.txt";

    public static void guardarContactos(List<Contacto> contactos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contacto contacto : contactos) {
                if (contacto instanceof Familiar) {
                    Familiar familiar = (Familiar) contacto;
                    writer.write("Familiar;" + familiar.getIdContacto() + ";" +
                            familiar.getNombreContacto() + ";" +
                            familiar.getNumeroContacto() + ";" +
                            familiar.getTipoRelacion());
                } else if (contacto instanceof Amigo) {
                    Amigo amigo = (Amigo) contacto;
                    String grupoNombre = (amigo.getGrupo() != null) ? amigo.getGrupo().getNombreGrupo() : "Sin grupo";
                    writer.write("Amigo;" + amigo.getIdContacto() + ";" +
                            amigo.getNombreContacto() + ";" +
                            amigo.getNumeroContacto() + ";" +
                            amigo.getApodo() + ";" + grupoNombre);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error al guardar los contactos: " + e.getMessage());
        }
    }

    public static void imprimirContactos(List<Contacto> contactos) {
        System.out.println("\n📒 Lista de Contactos:");

        System.out.println("\n- Familiares:");
        for (Contacto contacto : contactos) {
            if (contacto instanceof Familiar) {
                Familiar familiar = (Familiar) contacto;
                System.out.println("  - IdContacto: " + familiar.getIdContacto());
                System.out.println("  - Nombre: " + familiar.getNombreContacto());
                System.out.println("  - Número: " + familiar.getNumeroContacto());
                System.out.println("  - Tipo de Relación: " + familiar.getTipoRelacion());
                System.out.println();
            }
        }

        System.out.println("\n- Amigos:");
        for (Contacto contacto : contactos) {
            if (contacto instanceof Amigo) {
                Amigo amigo = (Amigo) contacto;
                String grupoNombre = (amigo.getGrupo() != null) ? amigo.getGrupo().getNombreGrupo() : "Sin grupo";
                System.out.println("  - IdContacto: " + amigo.getIdContacto());
                System.out.println("  - Nombre: " + amigo.getNombreContacto());
                System.out.println("  - Número: " + amigo.getNumeroContacto());
                System.out.println("  - Apodo: " + amigo.getApodo());
                System.out.println("  - Grupo: " + grupoNombre);
                System.out.println();
            }
        }
    }
}

