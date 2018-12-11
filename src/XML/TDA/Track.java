package XML.TDA;

import java.text.DecimalFormat;

/**
 * Created by usuario on 28/05/17.
 */
public class Track {
    private Integer trackId;
    private String name;
    private String album;
    private String composer;
    private String mediaType;
    private String artista;
    private String genre;
    private Integer milliseconds;
    private Integer bytes;
    private Double unitPrice;
    private Integer album_id;
    private Integer mediatype_id;
    private Integer genre_id;
    private String time;

    public Track(String name, String composer, Integer trackId, Integer album_id, Integer mediatype_id, Integer genre_id, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.name = name;
        this.composer = composer;
        this.trackId = trackId;
        this.album = album;
        this.album_id = album_id;
        this.mediaType = mediaType;
        this.mediatype_id = mediatype_id;
        this.genre = genre;
        this.genre_id = genre_id;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
        this.time = time;
    }

    public Track(Integer trackId, String name, String album, String artist, String mediaType, String genre, String composer, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.trackId = trackId;
        this.name = name;
        this.album = album;
        this.artista = artist;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
        Double tiempo =(milliseconds/1000.0)/60.0;
        DecimalFormat formato = new DecimalFormat("##.##");
        time =""+formato.format(tiempo)+" Minutos";
    }

    public Track(String name, Integer album_id, Integer mediatype_id, Integer genre_id, String composer, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.name = name;
        this.album_id = album_id;
        this.mediatype_id = mediatype_id;
        this.genre_id = genre_id;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public String getComposer() {
        return composer;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public String getArtist() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Integer getMediatype_id() {
        return mediatype_id;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public Integer getMilliseconds() {
        return milliseconds;
    }

    public Integer getBytes() {
        return bytes;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public String getTime() {
        return time;
    }
    public String toString() {
        return name;
    }
}
