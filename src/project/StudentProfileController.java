package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.model.Student;

import java.io.IOException;

public class StudentProfileController {

    public Label fNameLabel;
    public Label surnameLabel;
    public Label emailLabel;
    public Label streetLabel;
    public Label suburbLabel;
    public Label pCodeLabel;
    public Label cityLabel;
    public Button addModulesButton;

    Stage mainStage;

/*    public void setInformation(Student student){

        fNameLabel.setText(student.getFirstName());
        surnameLabel.setText(student.getLastName());
        emailLabel.setText(student.getLastName());
    }*/

    public void onClickedProfilePicButton(ActionEvent event) {

    }

    public void onClickViewModuleButton(ActionEvent event) {
    }

    public void onClickEditProfileDetailsButton(ActionEvent event) {
    }

    public void onClickAddModuleButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("views/addModuleView.fxml"));

        mainStage = (Stage)addModulesButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,1000,600));
    }

    public void onClickSessionButton(ActionEvent event) {
    }
}
