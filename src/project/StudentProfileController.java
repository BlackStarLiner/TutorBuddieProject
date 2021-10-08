package project;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.model.Student;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {


    public Button addModulesButton;
    public Button editProfileButton;

    public Label firstNameLabelProfile;
    public Label surnameLabelProfile;
    public Label emailLabelProfile;
    public Label streetLabelProfile;
    public Label suburbLabelProfile;
    public Label cityLabelProfile;
    public Label postalCodeLabelProfile;

    public ImageView profileImageView;

    Student student;
    Stage mainStage;

/*    public void setInformation(Student student){

        fNameLabel.setText(student.getFirstName());
        surnameLabel.setText(student.getLastName());
        emailLabel.setText(student.getLastName());
    }*/

    public void onClickedProfilePicButton(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png"));

        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile !=null){
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            profileImageView.setImage(image);
            profileImageView.setEffect(new DropShadow(20, Color.BLACK));
        }
    }

    public void onClickViewModuleButton(ActionEvent event) {
    }

    public void onClickEditProfileDetailsButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/studentEditProfileView.fxml"));
        mainStage = (Stage)editProfileButton.getScene().getWindow();
        mainStage.setScene(new Scene(root));
    }

    public void onClickSessionButton(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void setupStudentDisplay(){
        firstNameLabelProfile.setText(student.getFirstName());
        surnameLabelProfile.setText(student.getLastName());
        emailLabelProfile.setText(student.getEmail());
        streetLabelProfile.setText(student.getStreet());
        suburbLabelProfile.setText(student.getSuburb());
        cityLabelProfile.setText(student.getCity());
        cityLabelProfile.setText(student.getCity());
        postalCodeLabelProfile.setText(""+student.getpCode());
    }

    public void onClickAddModuleButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/addModuleView.fxml"));
        mainStage = (Stage)editProfileButton.getScene().getWindow();
        mainStage.setScene(new Scene(root));
    }

    // data received from outside classes
    public void receiveData(Student student, Stage mainStage){
        this.student = student;
        this.mainStage = mainStage;
        setupStudentDisplay();
    }

    public void onClickLanding(ActionEvent event) {
    }
}
