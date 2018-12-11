package XML.Escena;

import XML.TDA.Album;
import XML.TDA.Genero;
import XML.TDA.MediaType;
import XML.TDA.Track;
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
public class TrackC implements Initializable {

    @FXML
    private TextField txtNombre, txtComposer,txtMilli, txtBytes, txtPrecio;
    @FXML
    private ComboBox<Album> cmbAlbum;
    @FXML
    private ComboBox <MediaType> cmbMedio;
    @FXML
    private ComboBox <Genero> cmbGenre;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbAlbum.setItems(Main.con.getAlbums());
        cmbMedio.setItems(Main.con.getMedias());
        cmbGenre.setItems(Main.con.getGenres());
    }
    @FXML
    public void handleCancelarAction(ActionEvent e){
        Stage stage=(Stage)txtNombre.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void handleAceptarAction(ActionEvent e){
        if(Main.con.addTrack(new Track(txtNombre.getText(),
                cmbAlbum.getSelectionModel().getSelectedItem().getAlbum_id(),
                cmbMedio.getSelectionModel().getSelectedItem().getMedia_type_id(),
                cmbGenre.getSelectionModel().getSelectedItem().getId(),
                txtComposer.getText(),
                Integer.parseInt(txtMilli.getText()),
                Integer.parseInt(txtBytes.getText()),
                Double.parseDouble(txtPrecio.getText())))){

            Alert msg= new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("Exito!");
            msg.setHeaderText("La adicion fue realizada con exito");
            msg.setContentText("Puede consultar posteriormente la cancion en el catalogo.");
            msg.show();

        }
        else{
            Alert msg= new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Santas Batiseñales!");
            msg.setHeaderText("Hubo un error que no se tuvo contemplado");
            msg.setContentText("Puede volver a generar la compra revisando los campos o intentarlo mas tarde.");
            msg.show();

        }
    }
}
