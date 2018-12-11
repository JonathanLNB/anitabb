package XML.Escena;

import XML.TDA.MediaType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import sample.Main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class MediaC implements Initializable {

    @FXML
    private ListView<MediaType> listMedia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listMedia.setItems(Main.con.getMedias());
    }
    @FXML
    public void handleVerAction(ActionEvent e){
        MediaType aux=listMedia.getSelectionModel().getSelectedItems().get(0);
        Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Medio");
        msg.setContentText("");
        msg.setHeaderText(aux.getName());
        msg.show();
    }
    @FXML
    public void handleModificarAction(ActionEvent e){
        MediaType aux=listMedia.getSelectionModel().getSelectedItems().get(0);
        TextInputDialog dialog = new TextInputDialog(aux.getName());
        dialog.setTitle("Modificar el medio");
        dialog.setHeaderText("El nombre del medio "+aux.getName()+" sera modificado.");
        dialog.setContentText("Porfavor, ingrese el nuevo nombre:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(Main.con.modifyMedia(aux, new MediaType(result.get()))){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Medio");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getName()+" ha sido modificado");
                msg.show();
                listMedia.setItems(Main.con.getMedias());
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Medio");
                msg.setContentText("Ha sido imposible conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getName());
                msg.show();

            }
        }

    }
    @FXML
    public void handleEliminarAction(ActionEvent e){

        MediaType aux=listMedia.getSelectionModel().getSelectedItems().get(0);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Medio");
        alert.setHeaderText("El medio de reproduccion "+aux.getName()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (!Main.con.media_is_used(aux)){
                if(Main.con.deleteMedia(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Medio");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getName()+" eliminado con exito.");
                    msg.show();
                    listMedia.setItems(Main.con.getMedias());
                }
                else{
                    Alert error =new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Imposible eliminar el medio");
                    error.setContentText("No ha sido posible conectarse a la base de datos");
                    error.show();
                }
            }
            else{
                Alert error =new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Imposible eliminar artista");
                error.setContentText("El medio "+aux.getName()+" es empleado en almenos un track.\n"
                        + "No es posible eliminar medios que tengan tracks activos. \n"
                        + "Si desea continuar elimine primero los tracks");
                error.show();
            }
        }

    }
}
