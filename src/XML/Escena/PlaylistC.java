package XML.Escena;

import XML.TDA.PlayList;
import XML.TDA.Track;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import sample.Main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class PlaylistC implements Initializable {

    @FXML
    private ListView<PlayList> listPlaylist;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listPlaylist.setItems(Main.con.getPlayLists());
    }
    @FXML
    public void handleVerAction() {
        PlayList aux= listPlaylist.getSelectionModel().getSelectedItem();

        Dialog dialog = new Dialog();
        dialog.setTitle("Ver playlist");
        dialog.setHeaderText("Información de "+aux.getName()+".");
        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        VBox box=new VBox();
        ListView<Track> listCanciones= new ListView<Track>();
        listCanciones.setItems(Main.con.getPlaylistSongs(aux));
        box.getChildren().add(listCanciones);
        dialog.getDialogPane().setContent(box);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnAceptar) {
                dialog.close();
            }
            return null;
        });
        dialog.show();
    }
    @FXML
    public void handleEliminar(){
        PlayList aux= listPlaylist.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Playlist");
        alert.setHeaderText("La PlayList "+aux.getName()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(Main.con.deletePlaylist(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Playlist");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getName()+" eliminada con exito.");
                    msg.show();
                    listPlaylist.setItems(Main.con.getPlayLists());
            }
            else{
                    Alert error =new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Imposible eliminar la Playlist");
                    error.setContentText("No ha sido posible conectarse a la base de datos");
                    error.show();
            }
        }
        else{
                Alert error =new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Imposible eliminar empleado");
                error.setContentText("La Playlist "+aux.getName()+" posee al menos una pista.\n"
                        + "No es posible eliminar playlists que tengan pistas. \n"
                        + "Si desea continuar elimine primero todas las pistas.");
                error.show();
        }
    }
}
