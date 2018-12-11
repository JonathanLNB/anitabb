package XML.Escena;

import XML.TDA.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class EmpleadoNC implements Initializable {

    @FXML
    private TextField txtNombre, txtApellido, txtTitulo,
            txtAddress, txtCity, txtState,txtCountry, txtCP, txtPhone, txtFax, txtEmail;
    @FXML
    private DatePicker dpFechaNac, dpFechaCon;
    @FXML
    private ComboBox<Empleado> cmbJefe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbJefe.setItems(Main.con.getEmployees());
    }
    @FXML
    private void handleAceptarAction(ActionEvent event) {
        Empleado nuevoEmpleado;
        try{
            Integer jefeId = null;
            String jefe = null;
            if(cmbJefe.getItems().size()!=0){
                jefeId = cmbJefe.getSelectionModel().getSelectedItem().getEmp_no();
                jefe = cmbJefe.getSelectionModel().getSelectedItem().toString();
            }
            Date fechaNac=Date.valueOf(dpFechaNac.getValue());
            Date fechaHire=Date.valueOf(dpFechaCon.getValue());
            nuevoEmpleado=new Empleado(txtApellido.getText(),
                    txtNombre.getText(),
                    txtTitulo.getText(),
                    jefeId,
                    fechaNac,
                    fechaHire,
                    txtAddress.getText(),
                    txtCity.getText(),
                    txtState.getText(),
                    txtCountry.getText(),
                    txtCP.getText(),
                    txtPhone.getText(),
                    txtFax.getText(),
                    txtEmail.getText(),
                    jefe
            );

            if(Main.con.agregarEmpleado(nuevoEmpleado)){
                Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Conexion exitosa");
                msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                msg.setHeaderText("Empleado guardado exitosamente");
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
