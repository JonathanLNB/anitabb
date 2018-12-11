package XML.TDA;

/**
 * Created by usuario on 28/05/17.
 */
public class Artista {
    private Integer id;
    private String nombre;

    public Artista(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Artista(String s) {
        nombre = s;
    }

    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String toString() {
        return nombre;
    }

}
