package project;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import project.model.Student;

public class StudentProfileController {

    public Label fNameLabel;
    public Label surnameLabel;
    public Label emailLabel;
    public Label streetLabel;
    public Label suburbLabel;
    public Label pCodeLabel;
    public Label cityLabel;



    public void setInformation(Student student){

        fNameLabel.setText(student.getFirstName());
        surnameLabel.setText(student.getLastName());
        emailLabel.setText(student.getLastName());
    }

    public void onClickedProfilePicButton(ActionEvent event) {
    }
}
