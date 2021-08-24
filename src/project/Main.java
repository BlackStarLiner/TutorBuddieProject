package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {

    static Connection connection = null;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*();
        if(connection != null)
            System.out.println("Connection is successful");*/
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
        primaryStage.setTitle("Tutor Buddie");
        primaryStage.setScene(new Scene(root, 400,500));
        primaryStage.show();
/*        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TutorBuddie");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("mainView.fxml"));
        primaryLayout = loader.load();
        Scene scene = new Scene(primaryLayout);
        primaryStage.setScene(scene);
        primaryStage.show();*/

    }
/*
    public static void connectToDB() throws SQLException, ClassNotFoundException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
         connection = DriverManager.getConnection("jdbc:ucanaccess://D://School//2021//WRRV301 - Project//S2//Implementation//HornetsProject1.accdb");
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("select * from Student");
         while (resultSet.next())
             System.out.println(resultSet.getString(2));
         connection.close();
    }*/

}
