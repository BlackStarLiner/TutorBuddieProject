package project.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane primaryLayout;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TutorBuddie");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("mainView.fxml"));
        primaryLayout = loader.load();
        Scene scene = new Scene(primaryLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
