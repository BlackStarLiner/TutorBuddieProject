package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
        primaryStage.setTitle("Tutor Buddie");
        primaryStage.setScene(new Scene(root, 1000,600));
        primaryStage.show();
    }


}
