package XML.Escena;

import XML.TDA.Genero;
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
public class GeneroConC implements Initializable {

    @FXML
    private ListView<Genero> listGenre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listGenre.setItems(Main.con.getGenres());
    }
    @FXML
    public void handleVerAction(ActionEvent e){
        Genero aux=listGenre.getSelectionModel().getSelectedItems().get(0);
        Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Genero");
        msg.setContentText("");
        msg.setHeaderText(aux.getNombre());
        msg.show();
    }
    @FXML
    public void handleModificarAction(ActionEvent e){
        Genero aux=listGenre.getSelectionModel().getSelectedItems().get(0);
        TextInputDialog dialog = new TextInputDialog(aux.getNombre());
        dialog.setTitle("Modificar el medio");
        dialog.setHeaderText("El nombre del medio "+aux.getNombre()+" sera modificado.");
        dialog.setContentText("Porfavor, ingrese el nuevo nombre:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(Main.con.modifyGenre(aux, new Genero(result.get()))){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Medio");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getNombre()+" ha sido modificado");
                msg.show();
                listGenre.setItems(Main.con.getGenres());
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Medio");
                msg.setContentText("Ha sido imposible conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getNombre());
                msg.show();

            }
        }

    }
    @FXML
    public void handleEliminarAction(ActionEvent e){

        Genero aux=listGenre.getSelectionModel().getSelectedItems().get(0);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Genero");
        alert.setHeaderText("El genero "+aux.getNombre()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (!Main.con.genre_has_songs(aux)){
                if(Main.con.deleteGenre(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Genero");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getNombre()+" eliminado con exito.");
                    msg.show();
                    listGenre.setItems(Main.con.getGenres());
                }
                else{
                    Alert error =new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Imposible eliminar el genero");
                    error.setContentText("No ha sido posible conectarse a la base de datos");
                    error.show();
                }
            }
            else{
                Alert error =new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Imposible eliminar genero");
                error.setContentText("El genero "+aux.getNombre()+" es empleado en almenos un track.\n"
                        + "No es posible eliminar generos que tengan tracks activos. \n"
                        + "Si desea continuar elimine primero los tracks");
                error.show();
            }
        }
    }
}
