package XML.Escena;

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
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by usuario on 28/05/17.
 */
public class EmpleadoC implements Initializable {


    @FXML
    TableView<Empleado> tableEmpleados;
    @FXML
    TableColumn tabcolId, tabcolNombre, tabcolApellidos, tabcolTitulo, tabcolEmail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableEmpleados.setEditable(true);
        tabcolId.setCellValueFactory(new PropertyValueFactory<Empleado,String>("emp_no"));
        tabcolNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("first_name"));
        tabcolApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("last_name"));
        tabcolTitulo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("title"));
        tabcolEmail.setCellValueFactory(new PropertyValueFactory<Empleado,String>("email"));

        tableEmpleados.setItems(Main.con.getEmployees());
    }

    @FXML
    public void handleVerAction(ActionEvent e){
        Empleado aux=tableEmpleados.getSelectionModel().getSelectedItem();
        Dialog dialog = new Dialog();
        dialog.setTitle("Ver el empleado");
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
        grid.add(new Label(aux.getAdress()), 3, 0);

        grid.add(new Label("Apellido"), 0, 1);
        grid.add(new Label(aux.getLast_name()), 1, 1);
        grid.add(new Label("Ciudad"), 2, 1);
        grid.add(new Label(aux.getCity()), 3, 1);

        grid.add(new Label("Titulo"), 0, 2);
        grid.add(new Label(aux.getTitle()), 1, 2);
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
        grid.add(new Label("Jefe"), 2, 5);
        grid.add(new Label(aux.getBoss()), 3, 5);

        grid.add(new Label("Fecha de nacimiento"), 0, 6);
        grid.add(new Label(aux.getBirth_date().toString()), 1, 6);
        grid.add(new Label("Fecha de contratacion"), 2, 6);
        grid.add(new Label(aux.getHire_date().toString()), 3, 6);

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
        Empleado old=tableEmpleados.getSelectionModel().getSelectedItem();
        System.out.println(old.getEmp_no());
        Dialog dialog = new Dialog();
        dialog.setTitle("Modificar empleado");
        dialog.setHeaderText("Modificar información de "+old.getFirst_name()+".");
        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        TextField txtNombre= new TextField(old.getFirst_name()),
                txtAddress=new TextField(old.getAdress()),
                txtApellido=new TextField(old.getLast_name()),
                txtCity=new TextField(old.getCity()),
                txtTitle=new TextField(old.getTitle()),
                txtState=new TextField(old.getState()),
                txtPhone= new TextField(old.getPhone()),
                txtCountry=new TextField(old.getCountry()),
                txtFax=new TextField(old.getFax()),
                txtCP= new TextField(old.getPostal_code()),
                txtEmail=new TextField(old.getEmail());
        ComboBox<Empleado> cmbJefe= new ComboBox<Empleado>();
        ObservableList<Empleado> emps =Main.con.getEmployees();
        cmbJefe.setItems(emps);
        for(int i = 0; i<emps.size();i++)
            if(emps.get(i).getFirst_name().equals(old.getFirst_name()))
                cmbJefe.getSelectionModel().select(i);
        DatePicker dpFechaNac= new DatePicker(),
                dpFechaHire=new DatePicker ();
        dpFechaNac.getEditor().setText(""+old.getBirth_date());
        dpFechaHire.getEditor().setText(""+old.getHire_date());
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
        grid.add(txtTitle, 1, 2);
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
        grid.add(new Label("Jefe"), 2, 5);
        grid.add(cmbJefe, 3, 5);
        grid.add(new Label("Fecha Nacimiento"), 0, 6);
        grid.add(dpFechaNac, 1, 6);
        grid.add(new Label("Fecha Contratacion"), 2, 6);
        grid.add(dpFechaHire, 3, 6);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((Object dialogButton) -> {
            if (dialogButton == btnAceptar) {
                Empleado aux= new Empleado(txtNombre.getText(),
                        txtApellido.getText(),
                        txtTitle.getText(),
                        cmbJefe.getSelectionModel().getSelectedItem().getEmp_no(),
                        Date.valueOf(dpFechaNac.getValue()),
                        Date.valueOf(dpFechaHire.getValue()),
                        txtAddress.getText(),
                        txtCity.getText(),
                        txtState.getText(),
                        txtCountry.getText(),
                        txtCP.getText(),
                        txtPhone.getText(),
                        txtFax.getText(),
                        txtEmail.getText(),
                        cmbJefe.getSelectionModel().getSelectedItem().toString());
                if(Main.con.modifyEmploye(old, aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Empleado");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(old.getFirst_name()+" ha sido modificado");
                    msg.show();
                    tableEmpleados.setItems(Main.con.getEmployees());
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
        Empleado aux=tableEmpleados.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar empleado");
        alert.setHeaderText("El empleado "+aux.getFirst_name()+" sera eliminado." );
        alert.setContentText("¿Desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (!Main.con.employe_has_customers(aux)){
                if(Main.con.deleteEmployee(aux)){
                    Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
                    msg.setTitle("Empleado");
                    msg.setContentText("Ha sido exitoso conectarse a la conectarse con la base de datos");
                    msg.setHeaderText(aux.getFirst_name()+" eliminado con exito.");
                    msg.show();
                    tableEmpleados.setItems(Main.con.getEmployees());
                }
                else{
                    Alert error =new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Imposible eliminar el empleado");
                    error.setContentText("No ha sido posible conectarse a la base de datos");
                    error.show();
                }
            }
            else{
                Alert error =new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Imposible eliminar empleado");
                error.setContentText("El empleado "+aux.getFirst_name()+" posee almenos un cliente.\n"
                        + "No es posible eliminar empleados que tengan clientes activos. \n"
                        + "Si desea continuar elimine primero los clientes");
                error.show();
            }
        }
    }

}
