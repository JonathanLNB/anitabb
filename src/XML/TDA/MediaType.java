package XML.TDA;

/**
 * Created by usuario on 28/05/17.
 */
public class MediaType {
    private Integer media_type_id;
    private String name;

    public MediaType(Integer media_type_id, String name) {
        this.media_type_id = media_type_id;
        this.name = name;
    }

    public MediaType(String s) {
        name = s;
    }

    public Integer getMedia_type_id() {
        return media_type_id;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return name;
    }
}
