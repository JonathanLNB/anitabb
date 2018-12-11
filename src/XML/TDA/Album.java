package XML.TDA;

/**
 * Created by usuario on 28/05/17.
 */
public class Album {
    private Integer album_id;
    private String nombre;
    private Integer artista_id;
    private String artist;

    public Album(Integer album_id, String nombre, Integer artista_id, String artist) {
        this.album_id = album_id;
        this.nombre = nombre;
        this.artista_id = artista_id;
        this.artist = artist;
    }

    public Album(String titulo, Integer id) {
        this.nombre = titulo;
        this.artista_id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public Integer getAlbum_id() {
        return album_id;
    }
    public Integer getArtista_id() {
        return artista_id;
    }
    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
