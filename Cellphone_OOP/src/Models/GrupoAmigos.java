package Models;

import Models.Amigo;

import java.util.List;

public class GrupoAmigos {
    private String nombreGrupo;
    private List<Amigo> miembros;

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public List<Amigo> getMiembros() {
        return miembros;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public void setMiembros(List<Amigo> miembros) {
        this.miembros = miembros;
    }

    public GrupoAmigos(String nombreGrupo, List<Amigo> miembros) {
        this.nombreGrupo = nombreGrupo;
        this.miembros = miembros;
    }
}
