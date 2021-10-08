package project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    //region Cached values
    public Button requestTutButton;

    public Stage mainStage;
    public ImageView imageViewMain;

    public Button titleButton;

    public Button profileButton;
    public Button mainLoginButton;

    public Student student;
    public Label textLabelMain;
    //endregion


    // handles event when the login button is clicked
    public void onClickedMainLogin(ActionEvent event) throws IOException {
        mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        callLoginStage(mainStage);
    }


    @FXML
    private void actionOnClickReqButton(Event event) throws Exception {

        mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/registerView.fxml"));
        Parent root = loader.load();

        // setup scene for adding session
        Scene regScene = new Scene(root);

        // pass data to the student login controller
        StudentRegistrationController loginController = loader.getController();
        loginController.receivedData(mainStage, mainLoginButton);

        // setup stage for the registration view
        Stage loginStage = new Stage();
        loginStage.setTitle("Tutor Buddie - REGISTRATION");
        loginStage.initOwner(mainStage);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(regScene);
        loginStage.show();
    }

    public void onClickedProfileButton() throws IOException {

        System.out.println("cur student name: " + student.getFirstName());
        System.out.println("Method - onClickedProfleBtoon");

        if(student != null) {
            Parent root = FXMLLoader.load(getClass().getResource("views/studentProfileView.fxml"));
            StudentProfileController studentProfileController = new StudentProfileController();
            studentProfileController.receiveData(student, mainStage);
            mainStage.setScene(new Scene(root));
        }
        else{
            callLoginStage(mainStage);
        }
    }


    public void recievedData(Student student, Button mainLoginButton){
        System.out.println("cur student name: " + student.getFirstName());
        System.out.println("Method - receivedData");
        this.student = student;
        this.mainLoginButton = mainLoginButton;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    // calls the login stage
    private void callLoginStage(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/loginView.fxml"));

        Parent root = loader.load();

        // setup scene for adding session
        Scene loginScene = new Scene(root);

        // pass data to the student login controller
        LoginController loginController = loader.getController();
        loginController.receivedData(mainStage, mainLoginButton);

        // setup stage for the login view
        Stage loginStage = new Stage();
        loginStage.setTitle("Tutor Buddie - LOGIN PAGE");
        loginStage.initOwner(mainStage);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(loginScene);
        loginStage.show();
    }
}

