package XML.TDA;

/**
 * Created by usuario on 28/05/17.
 */
public class PlayListTrack {
    private Integer playlisttrack_id;
    private Integer track_id;

    public PlayListTrack(Integer playlisttrack_id, Integer track_id) {
        this.playlisttrack_id = playlisttrack_id;
        this.track_id = track_id;
    }

    public Integer getPlaylisttrack_id() {
        return playlisttrack_id;
    }

    public Integer getTrack_id() {
        return track_id;
    }
}
