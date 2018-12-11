package XML.Escena;

import XML.TDA.Artista;
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
public class ArtistaConC implements Initializable {

    @FXML
    private ListView<Artista> listArtista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listArtista.setItems(Main.con.getArtists());
    }
    @FXML
    public void handleVerAction(ActionEvent e){
        Artista aux=listArtista.getSelectionModel().getSelectedItems().get(0);
        Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Artista");
        msg.setContentText("");
        msg.setHeaderText(aux.getNombre());
        msg.show();
    }
    @FXML
    public void handleModificarAction(ActionEvent e){
        Artista aux=listArtista.getSelectionModel().getSelectedItems().get(0);
        TextInputDialog dialog = new TextInputDialog(aux.getNombre());
        dialog.setTitle("Modificar Artista");
        dialog.setHeaderText("El nombre del artista "+aux.getNombre()+" sera modificado.");
        dialog.setContentText("Porfavor, ingrese el nuevo nombre:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(Main.con.modifyArtist(aux, new Artista(result.get()))){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Artista");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getNombre()+" ha sido modificado");
                msg.show();
                listArtista.setItems(Main.con.getArtists());
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Artista");
                msg.setContentText("Ha sido imposible conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getNombre());
                msg.show();

            }
        }

    }
    @FXML
    public void handleEliminarAction(ActionEvent e){

        Artista aux=listArtista.getSelectionModel().getSelectedItems().get(0);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Artista");
        alert.setHeaderText("El artista "+aux.getNombre()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (!Main.con.artist_has_album(aux)){
                if(Main.con.deleteArtist(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Artista");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getNombre()+" eliminado con exito.");
                    msg.show();
                    listArtista.setItems(Main.con.getArtists());
                }
                else{
                    Alert error =new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Imposible eliminar artista");
                    error.setContentText("No ha sido posible conectarse a la base de datos");
                    error.show();
                }
            }
            else{
                Alert error =new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Imposible eliminar artista");
                error.setContentText("El artista "+aux.getNombre()+" posee almenos un album.\n"
                        + "No es posible eliminar artistas que tengan albumes activos. \n"
                        + "Si desea continuar elimine primero los albumes");
                error.show();
            }
        }

    }

}
