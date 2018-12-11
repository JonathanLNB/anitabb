package XML.Escena;

import XML.TDA.Cliente;
import XML.TDA.Empleado;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class ClienteConC implements Initializable {

    @FXML
    TableView<Cliente> tableCompradores;
    @FXML
    TableColumn tabcolNombre, tabcolApellidos, tabcolEmail, tabcolAgente;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableCompradores.setEditable(true);
        tabcolAgente.setCellValueFactory(new PropertyValueFactory<Cliente,String>("sales_agent"));
        tabcolNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("first_name"));
        tabcolApellidos.setCellValueFactory(new PropertyValueFactory<Cliente,String>("last_name"));
        tabcolEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));

        tableCompradores.setItems(Main.con.getCompradores());
    }
    @FXML
    public void handleVerAction(ActionEvent e){
        Cliente aux=tableCompradores.getSelectionModel().getSelectedItem();
        Dialog dialog = new Dialog();
        dialog.setTitle("Ver el comprador");
        dialog.setHeaderText("Información de "+aux.getFirst_name()+".");
        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nombre"), 0, 0);
        grid.add(new Label(aux.getFirst_name()), 1, 0);
        grid.add(new Label("Direccion"), 2, 0);
        grid.add(new Label(aux.getAddress()), 3, 0);

        grid.add(new Label("Apellido"), 0, 1);
        grid.add(new Label(aux.getLast_name()), 1, 1);
        grid.add(new Label("Ciudad"), 2, 1);
        grid.add(new Label(aux.getCity()), 3, 1);

        grid.add(new Label("Compania"), 0, 2);
        grid.add(new Label(aux.getCompany()), 1, 2);
        grid.add(new Label("Estado"), 2, 2);
        grid.add(new Label(aux.getState()), 3, 2);

        grid.add(new Label("Telefono"), 0, 3);
        grid.add(new Label(aux.getPhone()), 1, 3);
        grid.add(new Label("Pais"), 2, 3);
        grid.add(new Label(aux.getCountry()), 3, 3);

        grid.add(new Label("Fax"), 0, 4);
        grid.add(new Label(aux.getFax()), 1, 4);
        grid.add(new Label("Codigo Postal"), 2, 4);
        grid.add(new Label(aux.getPostal_code()), 3, 4);

        grid.add(new Label("Email"), 0, 5);
        grid.add(new Label(aux.getEmail()), 1, 5);
        grid.add(new Label("Agente de Ventas"), 2, 5);
        grid.add(new Label(aux.getSales_agent()), 3, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnAceptar) {
                dialog.close();
            }
            return null;
        });
        dialog.show();
    }
    @FXML
    public void handleModificarAction(ActionEvent e){

        Cliente old=tableCompradores.getSelectionModel().getSelectedItem();
        Dialog dialog = new Dialog();
        dialog.setTitle("Ver el comprador");
        dialog.setHeaderText("Modificar información de "+old.getFirst_name()+".");
        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        TextField txtNombre= new TextField(old.getFirst_name()),
                txtAddress=new TextField(old.getAddress()),
                txtApellido=new TextField(old.getLast_name()),
                txtCity=new TextField(old.getCity()),
                txtCompany=new TextField(old.getCompany()),
                txtState=new TextField(old.getState()),
                txtPhone= new TextField(old.getPhone()),
                txtCountry=new TextField(old.getCountry()),
                txtFax=new TextField(old.getFax()),
                txtCP= new TextField(old.getPostal_code()),
                txtEmail=new TextField(old.getEmail());
        ComboBox <Empleado>cmbSales= new ComboBox();
        ObservableList<Empleado> emp = Main.con.getVendedores();
        cmbSales.setItems(emp);
        for (int i = 0 ; i<emp.size(); i++)
            if(emp.get(i).getFirst_name().equals(old.getSales_agent().split(" ")[0]))
                cmbSales.getSelectionModel().select(i);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nombre"), 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(new Label("Direccion"), 2, 0);
        grid.add(txtAddress, 3, 0);
        grid.add(new Label("Apellido"), 0, 1);
        grid.add(txtApellido, 1, 1);
        grid.add(new Label("Ciudad"), 2, 1);
        grid.add(txtCity, 3, 1);
        grid.add(new Label("Compania"), 0, 2);
        grid.add(txtCompany, 1, 2);
        grid.add(new Label("Estado"), 2, 2);
        grid.add(txtState, 3, 2);
        grid.add(new Label("Telefono"), 0, 3);
        grid.add(txtPhone, 1, 3);
        grid.add(new Label("Pais"), 2, 3);
        grid.add(txtCountry, 3, 3);
        grid.add(new Label("Fax"), 0, 4);
        grid.add(txtFax, 1, 4);
        grid.add(new Label("Codigo Postal"), 2, 4);
        grid.add(txtCP, 3, 4);
        grid.add(new Label("Email"), 0, 5);
        grid.add(txtEmail, 1, 5);
        grid.add(new Label("Agente de Ventas"), 2, 5);
        grid.add(cmbSales, 3, 5);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((Object dialogButton) -> {
            if (dialogButton == btnAceptar) {
                Cliente aux= new Cliente(txtNombre.getText(),
                        txtApellido.getText(),
                        txtCompany.getText(),
                        txtAddress.getText(),
                        txtCity.getText(),
                        txtState.getText(),
                        txtCountry.getText(),
                        txtCP.getText(),
                        txtPhone.getText(),
                        txtFax.getText(),
                        txtEmail.getText(),
                        cmbSales.getSelectionModel().getSelectedItem().getEmp_no(),
                        cmbSales.getSelectionModel().getSelectedItem().toString());
                if(Main.con.modifyCustomer(old, aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Comprador");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(old.getFirst_name()+" ha sido modificado");
                    msg.show();
                    tableCompradores.setItems(Main.con.getCompradores());
                }
                else{
                    Alert msg=new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Album");
                    msg.setContentText("Ha sido imposible conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getFirst_name());
                    msg.show();

                }
            }
            return null;
        });
        dialog.show();

    }
    @FXML
    public void handleEliminarAction(ActionEvent e){
        Cliente aux=tableCompradores.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Comprador");
        alert.setHeaderText("El comprador "+aux.getFirst_name()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (!Main.con.customer_has_invoices(aux)){
                if(Main.con.deleteCustomer(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Comprador");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getFirst_name()+" eliminado con exito.");
                    msg.show();
                    tableCompradores.setItems(Main.con.getCompradores());
                }
                else{
                    Alert error =new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Imposible eliminar al comprador");
                    error.setContentText("No ha sido posible conectarse a la base de datos");
                    error.show();
                }
            }
            else{
                Alert error =new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Imposible eliminar comprador");
                error.setContentText("El cliente "+aux.getFirst_name()+" posee almenos una factura.\n"
                        + "No es posible eliminar clientes que tengan facturas activas. \n"
                        + "Si desea continuar elimine primero las facturas");
                error.show();
            }
        }
    }
}
