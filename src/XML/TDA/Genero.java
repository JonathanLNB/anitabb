package XML.TDA;

/**
 * Created by usuario on 28/05/17.
 */
public class Genero {
    private Integer id;
    private String nombre;
    private int cant;

    public Genero(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Genero( String nombre, int cant) {
        this.nombre = nombre;
        this.cant = cant;
    }

    public Genero(String s) {
        nombre = s;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCant() {
        return cant;
    }

    public String toString() {
        return nombre;
    }
}
