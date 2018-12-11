package XML.Escena;

import XML.TDA.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by usuario on 28/05/17.
 */
public class MenuC implements Initializable{
    public static ObservableList<Track> carrito;

    @FXML
    private MenuItem mPDF, mSalir;
    @FXML
    private ComboBox<Empleado> cmbVendedor;
    @FXML
    private ComboBox <Cliente>cmbComprador;
    @FXML
    private TableView tableCarrito;
    @FXML
    private TableColumn tabcolName, tabcolPrice;
    @FXML
    public  Label lTotal;
    @FXML
    private ImageView iMZone;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //mPDF.setGraphic(new ImageView("icons/pdf_icon.png"));
        cmbVendedor.setItems(Main.con.getVendedores());
        cmbComprador.setItems(Main.con.getCompradores());
        carrito= FXCollections.observableArrayList();
        tabcolName.setCellValueFactory(new PropertyValueFactory<Track,String>("name"));
        tabcolPrice.setCellValueFactory(new PropertyValueFactory<Track,Double>("unitPrice"));
        tableCarrito.setItems(carrito);
        iMZone.setImage(new Image(String.valueOf(getClass().getResource("../../mzone.png"))));
    }
    @FXML
    private void menu(){
        Main.pdf.canciones(new File("Canciones.pdf"));
        Main.pdf.Empleados(new File("Empleados.pdf"));
    }
    @FXML
    private void handleNewPlaylistAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar una playlist");
        dialog.setHeaderText("PlayList nueva");
        dialog.setContentText("Ingrese el nombre de la lista");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            Main.con.addPlayList(new PlayList(result.get()));
        }

    }
    @FXML
    private void handleViewPlaylistAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Playlist.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Playlist");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void handleCleanCarritoAction(ActionEvent event) {
        carrito.clear();
        lTotal.setText("Total: ");
    }
    @FXML
    private void handleEliminarAction(ActionEvent event) {
        try {
            carrito.remove(tableCarrito.getSelectionModel().getSelectedIndex());
            lTotal.setText("Total: $"+getTotal());
        }catch(Exception e){}
    }
    private double getTotal(){
        double aux=0;
        for(int i=0; i<carrito.size(); i++)
            aux+=carrito.get(i).getUnitPrice();
        return aux;
    }
    @FXML
    private void handleComprarAction(ActionEvent event) {
        double total=getTotal();
        Cliente comprador=cmbComprador.getSelectionModel().getSelectedItem();
        try{
            Invoice factura= new Invoice(comprador.getId(),
                    Date.valueOf(LocalDate.now()),
                    comprador.getAddress(),
                    comprador.getCity(),
                    comprador.getState(),
                    comprador.getCountry(),
                    comprador.getPostal_code(),
                    total);
            if(Main.con.addInvoice(factura)){
                Alert msg= new Alert(Alert.AlertType.CONFIRMATION);
                factura=Main.con.searchInvoice(factura);
                System.out.println(factura.getInvoice_id());
                for(int i=0; i<carrito.size(); i++){
                    Track aux=carrito.get(i);
                    System.out.println(Main.con.addInvoiceLine(new InvoiceLine(factura.getInvoice_id(),aux.getTrackId(), aux.getUnitPrice(), 1 )));
                }

                String archivo="";
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Factura");
                dialog.setHeaderText("Informacion de factura");
                dialog.setContentText("Ingrese el nombre del archivo de la factura");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    archivo=result.get();
                    Main.pdf.createPDFFactura(new File(archivo+".pdf"), carrito, comprador);
                    carrito.clear();
                    cmbVendedor.getSelectionModel().selectFirst();
                    cmbComprador.getSelectionModel().selectFirst();
                    lTotal.setText("Total: $");
                }
            }
            else{
                Alert msg= new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Santas Batiseñales!");
                msg.setHeaderText("Hubo un error que no se tuvo contemplado");
                msg.setContentText("Puede volver a generar la compra revisando los camposo intentarlo mas tarde.");
                msg.show();

            }
        }
        catch(Exception e){}

    }
    @FXML
    private void handleCancelarAction(ActionEvent event) {
        carrito.clear();
    }
    @FXML
    private void handleCalcularAction(){
        Double d = 0.0;
        for(int i = 0; i<MenuC.carrito.size(); i++){
            d+=MenuC.carrito.get(i).getUnitPrice();
        }
        lTotal.setText("Total: $"+d);
    }
    @FXML
    private void handleVerEmpleadoAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Empleado.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Empleados");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    @FXML
    private void handleVerCatalogoAction(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Catalogo.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Catalogo");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    @FXML
    private void handleNewTrackAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("TrackN.fxml"))));
            stage.setTitle("Nuevo Track");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleNewEmpleadoAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("EmpleadoN.fxml"))));
            stage.setTitle("Nuevo Empleado");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleNewArtistAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ArtistaN.fxml"))));
            stage.setTitle("Nuevo Artista");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleNewAlbumAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("AlbumN.fxml"))));
            stage.setTitle("Nuevo Album");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleNewGenreAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GeneroN.fxml"))));
            stage.setTitle("Nuevo Genero");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleNewMedioAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MedioN.fxml"))));
            stage.setTitle("Nuevo Medio");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleNewCustomerAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ClienteN.fxml"))));
            stage.setTitle("Nuevo Comprador");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleViewCustomerAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ClienteCon.fxml"))));
            stage.setTitle("Compradores");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleViewAlbumAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("AlbumCon.fxml"))));
            stage.setTitle("Albumes");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleViewArtistAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ArtistaCon.fxml"))));
            stage.setTitle("Artistas");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleViewGenreAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GeneroCon.fxml"))));
            stage.setTitle("Generos");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleViewMediaAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Media.fxml"))));
            stage.setTitle("Tipos de Medio");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void handleAcercaDe() {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        VBox panel = new VBox();
        panel.setSpacing(20);
        panel.setStyle("-fx-background-color: #ffffff");
        panel.setAlignment(Pos.CENTER);

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.HORIZONTAL);
        pane.setAlignment(Pos.CENTER);

        Label nombre, tarea, materia, carrera;
        ImageView logo;
        nombre = new Label("Nombres: Ana Elisa Gonzalez Gutierrez\n");
        nombre.setFont(new Font(15));
        carrera = new Label("Ing. Sistemas Computacionales.");
        carrera.setFont(new Font(15));
        materia = new Label("Tópicos Avanzados De Programación.");
        materia.setFont(new Font(20));
        tarea = new Label("Proyecto Final");
        tarea.setFont(new Font(18));
        File file = new File("Logos.PNG");
        Image image = new Image(file.toURI().toString());
        logo = new ImageView();
        System.out.println(file.getAbsoluteFile());
        logo.setImage(image);


        pane.getChildren().add(logo);
        panel.getChildren().addAll(pane, materia, tarea, nombre, carrera);

        msg.setTitle("Ver Información");
        msg.setHeaderText("Acerca De ");
        msg.getDialogPane().setContent(panel);
        msg.show();
    }
}
