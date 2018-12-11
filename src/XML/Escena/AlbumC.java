package XML.Escena;

import XML.TDA.Album;
import XML.TDA.Artista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class AlbumC implements Initializable {

    @FXML
    private TextField txtTitulo;
    @FXML
    private ComboBox<Artista> cmbArtista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbArtista.setItems(Main.con.getArtists());
    }

    @FXML
    public void handleAceptarAction(ActionEvent e){
        String titulo=txtTitulo.getText();
        if (!titulo.equals("")){
            Album al= new Album(titulo, cmbArtista.getSelectionModel().getSelectedItem().getId());
            if(Main.con.addAlbum(al)){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Conexion exitosa");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText("Album agregado exitosamente");
                msg.show();
                Stage stage = (Stage) txtTitulo.getScene().getWindow();
                stage.close();
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error en conexion");
                msg.setContentText("Se ha producido un error al conectarse con la base de datos");
                msg.setHeaderText("Imposible conectarse con servidor");
                msg.show();
                Stage stage = (Stage) txtTitulo.getScene().getWindow();
                stage.close();
            }

        }
    }
    @FXML
    public void handleBorrarAction(ActionEvent e){
        txtTitulo.setText("");
    }
    @FXML
    public void handleCancelarAction(ActionEvent e){
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }
}
