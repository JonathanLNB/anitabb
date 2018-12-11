package XML.Escena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class ArtistaC implements Initializable {


    @FXML
    TextField tNombre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    public void handleAceptarAction(ActionEvent e){
        String name=tNombre.getText();
        if (!name.equals("")){
            if(Main.con.addArtist(name)){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Conexion exitosa");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText("Artista agregado exitosamente");
                msg.show();
                Stage stage = (Stage) tNombre.getScene().getWindow();
                stage.close();
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error en conexion");
                msg.setContentText("Se ha producido un error al conectarse con la base de datos");
                msg.setHeaderText("Imposible conectarse con servidor");
                msg.show();
                Stage stage = (Stage) tNombre.getScene().getWindow();
                stage.close();
            }

        }
    }
    @FXML
    public void handleBorrarAction(ActionEvent e){
        tNombre.setText("");
    }
    @FXML
    public void handleCancelarAction(ActionEvent e){
        Stage stage = (Stage) tNombre.getScene().getWindow();
        stage.close();
    }
}
