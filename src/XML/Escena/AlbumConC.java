package XML.Escena;

import XML.TDA.Album;
import XML.TDA.Artista;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import sample.Main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class AlbumConC implements Initializable {


    @FXML
    private TableView<Album> tableAlbumes;
    @FXML
    private TableColumn tabcolTitulo, tabcolArtista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableAlbumes.setEditable(true);
        tabcolTitulo.setCellValueFactory(new PropertyValueFactory<Album,String>("nombre"));
        tabcolArtista.setCellValueFactory(new PropertyValueFactory<Album,String>("artist"));

        tableAlbumes.setItems(Main.con.getAlbums());
    }
    @FXML
    public void handleVerAction(ActionEvent e){
        Album aux=tableAlbumes.getSelectionModel().getSelectedItem();
        Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Medio");
        msg.setContentText(aux.getNombre()+" es un album del artista "+aux.getArtist());
        msg.setHeaderText(aux.getNombre());
        msg.show();

    }
    @FXML
    public void handleModificarAction(ActionEvent e){
        Album aux=tableAlbumes.getSelectionModel().getSelectedItem();
        Dialog<Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle("Modificar el album");
        dialog.setHeaderText("El album "+aux.getNombre()+" sera modificado.");
        ButtonType btnModificar = new ButtonType("Modificar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnModificar, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Titulo");
        title.setText(aux.getNombre());
        ComboBox<Artista> artist= new ComboBox();
        ObservableList<Artista> art = Main.con.getArtists();
        artist.setItems(art);
        for(int i =0; i<art.size(); i++)
            if(art.get(i).getId()==aux.getArtista_id())
                artist.getSelectionModel().select(i);

        grid.add(new Label("Titulo"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Artista"), 0, 1);
        grid.add(artist, 1, 1);



        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> title.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnModificar) {
                return new Pair<>(title.getText(), ""+artist.getSelectionModel().getSelectedItem().getId());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pairTitleArtist -> {
            if(Main.con.modifyAlbum(aux, new Album(pairTitleArtist.getKey(),Integer.parseInt(pairTitleArtist.getValue())))){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Medio");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getNombre()+" ha sido modificado");
                msg.show();
                tableAlbumes.setItems(Main.con.getAlbums());
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Album");
                msg.setContentText("Ha sido imposible conectarse a la conectarse con la base de datos");
                msg.setHeaderText(aux.getNombre());
                msg.show();

            }
        });
    }
    @FXML
    public void handleEliminarAction(ActionEvent e){

        Album aux=tableAlbumes.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar album");
        alert.setHeaderText("El album "+aux.getNombre()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (!Main.con.album_has_songs(aux)){
                if(Main.con.deleteAlbum(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Album");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getNombre()+" eliminado con exito.");
                    msg.show();
                    tableAlbumes.setItems(Main.con.getAlbums());
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
                error.setHeaderText("Imposible eliminar album");
                error.setContentText("El album "+aux.getNombre()+" posee almenos un track.\n"
                        + "No es posible eliminar albumes que tengan tracks activos. \n"
                        + "Si desea continuar elimine primero los tracks");
                error.show();
            }
        }
    }
}
