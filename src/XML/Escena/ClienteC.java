package XML.Escena;

import XML.TDA.Cliente;
import XML.TDA.Empleado;
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
public class ClienteC implements Initializable {

    @FXML
    private TextField txtNombre, txtApellido, txtCompania,
            txtAddress, txtCity, txtState,txtCountry, txtCP, txtPhone, txtFax, txtEmail;
    @FXML
    private ComboBox<Empleado> cmbVendedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbVendedor.setItems(Main.con.getVendedores());
    }
    @FXML
    private void handleAceptarAction(ActionEvent event) {
        Cliente nuevoComprador;
        try{
            Integer vendedorId=cmbVendedor.getSelectionModel().getSelectedItem().getEmp_no();
            String vendedor=cmbVendedor.getSelectionModel().getSelectedItem().toString();

            nuevoComprador=new Cliente(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtCompania.getText(),
                    txtAddress.getText(),
                    txtCity.getText(),
                    txtState.getText(),
                    txtCountry.getText(),
                    txtCP.getText(),
                    txtPhone.getText(),
                    txtFax.getText(),
                    txtEmail.getText(),
                    vendedorId,
                    vendedor
            );

            if(Main.con.addCustomer(nuevoComprador)){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Conexion exitosa");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText("Comprador guardado exitosamente");
                msg.show();
                Stage stage= (Stage) txtNombre.getScene().getWindow();
                stage.close();
            }
            else{
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error en conexion");
                msg.setContentText("Se ha producido un error al conectarse con la base de datos");
                msg.setHeaderText("Imposible conectarse con servidor");
                msg.show();
                Stage stage= (Stage) txtNombre.getScene().getWindow();
                stage.close();
            }

        }catch(Exception e){
            System.out.println("No se ha podido guardar adecuadamente el empleado");
        }
    }
    @FXML
    private void handleCancelarAction(ActionEvent event) {
        Stage stage= (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

}
