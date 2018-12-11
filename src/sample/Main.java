package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    public static MySQL con;
    public static PDF pdf = new PDF();

    @Override
    public void start(Stage stage) throws Exception {
        con=new MySQL();
        con.Conectar();
        Parent root = FXMLLoader.load(getClass().getResource("../XML/Escena/Menu.fxml"));
        root.setStyle("-fx-background-color: white");
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("MZone - Sistema de Tienda");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
