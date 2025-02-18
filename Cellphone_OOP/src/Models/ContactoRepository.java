package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoRepository {

    private static final String FILE_NAME = "contactos.txt";

    public static void guardarContactos(List<Contacto> contactos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contacto contacto : contactos) {
                if (contacto instanceof Familiar) {
                    Familiar familiar = (Familiar) contacto;
                    writer.write("Familiar;" +
                            familiar.getNombreContacto() + ";" +
                            familiar.getNumeroContacto() + ";" +
                            familiar.getTipoRelacion());
                } else if (contacto instanceof Amigo) {
                    Amigo amigo = (Amigo) contacto;
                    String grupoNombre = (amigo.getGrupo() != null) ? amigo.getGrupo().getNombreGrupo() : "Sin grupo";
                    writer.write("Amigo;" +
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

    public static List<Contacto> cargarContactos() {
        List<Contacto> contactos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length > 0) {
                    String tipo = datos[0];
                    String nombre = datos[1];
                    int numero = Integer.parseInt(datos[2]);

                    if ("Familiar".equals(tipo) && datos.length == 4) {
                        String tipoRelacion = datos[3];
                        contactos.add(new Familiar(nombre, numero, tipoRelacion));
                    } else if ("Amigo".equals(tipo) && datos.length == 5) {
                        String apodo = datos[3];
                        String nombreGrupo = datos[4];
                        GrupoAmigos grupo = nombreGrupo.equals("Sin grupo") ? null : new GrupoAmigos(nombreGrupo, new ArrayList<>());
                        contactos.add(new Amigo(nombre, numero, apodo, grupo));
                    }
                }
            }
            System.out.println("✅ Contactos cargados correctamente desde el archivo.");
        } catch (IOException e) {
            System.err.println("⚠️ No se pudo leer el archivo de contactos: " + e.getMessage());
        }
        return contactos;
    }
}


