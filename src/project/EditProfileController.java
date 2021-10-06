package project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    public Button saveButtonEditProfile;

    public TextField nameEditProfile;
    public TextField surnameEditProfile;
    public TextField emailEditProfile;
    public TextField streetEditProfile;
    public TextField suburbEditProfile;
    public TextField cityEditProfile;
    public TextField pCodeEditProfile;

    Student student;
    DatabaseUtil dbUtil;

    Stage mainStage;

    public void onClickedProfilePicButton(ActionEvent event) {
    }

    public void onClickViewModuleButton(ActionEvent event) {
    }

    public void onClickSessionButton(ActionEvent event) {
    }

    public void onClickEditProfileDetailsButton(ActionEvent event) {
    }

    public void onClickAddModuleButton(ActionEvent event) {
    }

    public void onClickSaveEditProfile(ActionEvent event) {
        nameEditProfile.setText(student.getFirstName());
        surnameEditProfile.setText(student.getLastName());
        emailEditProfile.setText(student.getEmail());
        streetEditProfile.setText(student.getStreet());
        suburbEditProfile.setText(student.getSuburb());
        cityEditProfile.setText(student.getCity());
        pCodeEditProfile.setText(""+student.getpCode());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbUtil = new DatabaseUtil();
        student =   new Student(new SimpleIntegerProperty(1), new SimpleStringProperty("Philasande"), new SimpleStringProperty("Tono"),
                new SimpleStringProperty("philasande@gmail.com"), new SimpleStringProperty("University Way"),
                new SimpleStringProperty("Gqeberha"), new SimpleStringProperty("Summerstrand"),
                new SimpleIntegerProperty(6001));
        updateDetails();
    }

    private void updateDetails(){

        nameEditProfile.setText(student.getFirstName());
        surnameEditProfile.setText(student.getLastName());
        emailEditProfile.setText(student.getEmail());
        streetEditProfile.setText(student.getStreet());
        suburbEditProfile.setText(student.getSuburb());
        cityEditProfile.setText(student.getCity());
        pCodeEditProfile.setText(""+student.getpCode());

        saveButtonEditProfile.setOnAction(event -> {
            student.setFirstName(nameEditProfile.getText());
            student.setLastName(surnameEditProfile.getText());
            student.setEmail(emailEditProfile.getText());
            student.setStreet(streetEditProfile.getText());
            student.setSuburb(suburbEditProfile.getText());
            student.setCity(cityEditProfile.getText());
            student.setpCode(Integer.parseInt(pCodeEditProfile.getText()));
        });
    }
}
