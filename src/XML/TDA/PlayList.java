package XML.TDA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 28/05/17.
 */
public class PlayList {
    private Integer playlist_id;
    private String name;
    private List<Track> canciones=new ArrayList<Track>();

    public PlayList(Integer playlist_id, String name, List<Track> canciones) {
        this.playlist_id = playlist_id;
        this.name = name;
        this.canciones = canciones;
    }

    public PlayList(Integer playlist_id, String name) {
        this.playlist_id = playlist_id;
        this.name = name;
    }

    public PlayList(String s) {
        this.name = s;
    }

    public Integer getPlaylist_id() {
        return playlist_id;
    }

    public String getName() {
        return name;
    }

    public List<Track> getCanciones() {
        return canciones;
    }
    public String toString() {
        return name;
    }
}
