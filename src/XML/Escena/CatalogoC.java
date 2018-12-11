package XML.Escena;

import XML.TDA.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import sample.Main;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class CatalogoC implements Initializable{
    @FXML TableView tCanciones;
    @FXML TableColumn cName, cAlbum, cArtist, cGenre, cTime;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tCanciones.setEditable(true);
        cName.setCellValueFactory(new PropertyValueFactory<Track,String>("name"));
        cAlbum.setCellValueFactory(new PropertyValueFactory<Track,String>("album"));
        cArtist.setCellValueFactory(new PropertyValueFactory<Track,String>("composer"));
        cGenre.setCellValueFactory(new PropertyValueFactory<Track,String>("genre"));
        cTime.setCellValueFactory(new PropertyValueFactory<Track,String>("time"));
        tCanciones.setItems(Main.con.getSongs());
    }
    public void buy(){
        MenuC.carrito.add((Track)tCanciones.getSelectionModel().getSelectedItem());
    }
    public void addToPlayList(){
        ObservableList<PlayList> l=Main.con.getPlayLists();
        ChoiceDialog<PlayList> dialog;
        dialog = new ChoiceDialog <PlayList>(l.get(0), l );
        dialog.setTitle("Agregar a PlayList");
        dialog.setHeaderText("Seleccione la playlist");
        dialog.setContentText("Seleccione:");

        Optional<PlayList> result = dialog.showAndWait();
        if (result.isPresent()){
            PlayList pl=result.get();
            Main.con.addItemToPlayList((Track)tCanciones.getSelectionModel().getSelectedItem(), pl);
        }
    }

    public void modificar() {
        ObservableList<Album> albums = Main.con.getAlbums();
        ObservableList<MediaType> mediaTypes = Main.con.getMedias();
        ObservableList<Genero> generos = Main.con.getGenres();
        Track old = (Track) tCanciones.getSelectionModel().getSelectedItem();
        Dialog dialog = new Dialog();
        dialog.setTitle("Modificar Track");
        dialog.setHeaderText("Modificar información de "+old.getName()+".");
        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        ComboBox<Album> cAlbums = new ComboBox<Album>(albums);
        ComboBox<MediaType> cMedia = new ComboBox<MediaType>(mediaTypes);
        ComboBox<Genero> cGeneros = new ComboBox<Genero>(generos);

        TextField txtNombre= new TextField(old.getName());
        TextField txtCompositor= new TextField(old.getComposer());
        TextField txtMilli= new TextField(""+old.getMilliseconds());
        TextField txtBytes= new TextField(old.getBytes()+"");
        TextField txtPrecio= new TextField(old.getUnitPrice()+"");

        for(int i = 0; i<albums.size(); i++)
            if(old.getAlbum().equals(albums.get(i).getNombre()))
                cAlbums.getSelectionModel().select(i);
        for(int i = 0; i<mediaTypes.size(); i++)
            if(old.getMediaType().equals(mediaTypes.get(i).getName()))
                cMedia.getSelectionModel().select(i);
        for(int i = 0; i<generos.size(); i++)
            if(old.getGenre().equals(generos.get(i).getNombre()))
                cGeneros.getSelectionModel().select(i);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nombre"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Album"), 2, 0);
        grid.add(cAlbums, 3, 0);
        grid.add(new Label("Media Type"), 0, 1);
        grid.add(cMedia, 1, 1);
        grid.add(new Label("Genero"), 2, 1);
        grid.add(cGeneros, 3, 1);
        grid.add(new Label("Compositor"), 0, 2);
        grid.add(txtCompositor, 1, 2);
        grid.add(new Label("Bytes"), 2, 2);
        grid.add(txtBytes, 3, 2);
        grid.add(new Label("Millisegundos"), 0, 3);
        grid.add(txtMilli, 1, 3);
        grid.add(new Label("Precio"), 2, 3);
        grid.add(txtPrecio, 3, 3);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((Object dialogButton) -> {
            if (dialogButton == btnAceptar) {
                Track aux = new Track(txtNombre.getText(), txtCompositor.getText(), old.getTrackId(), cAlbums.getSelectionModel().getSelectedItem().getAlbum_id(), cMedia.getSelectionModel().getSelectedItem().getMedia_type_id(), cGeneros.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(txtMilli.getText()+""), Integer.parseInt(""+txtBytes.getText()), Double.parseDouble(""+txtPrecio.getText()));
                if(Main.con.modifyTrack(old, aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Track");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(old.getName()+" ha sido modificado");
                    msg.show();
                    tCanciones.setItems(Main.con.getSongs());
                }
                else{
                    Alert msg=new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Album");
                    msg.setContentText("Ha sido imposible conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getName());
                    msg.show();

                }
            }
            return null;
        });
        dialog.show();
    }
}
