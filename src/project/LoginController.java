package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //region Cached values
    public Stage mainStage;
    Student student;
    public Label labelUserName;
    public Label labelPassword;
    public Button cancelPageButton;
    public TextField userNameTextFieldLoginPage;
    public PasswordField passTextFieldLoginPage;
    public Button loginButton;
    //endregion

    public DatabaseUtil dbUtil = new DatabaseUtil();

    /**
     * method handles events that occur when the log in button is clicked
     * a new stage if created and allows input for username and password
     * student that corresponds to details provided is retrieved from the database
     */
    public void onClickLoginButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String userName = userNameTextFieldLoginPage.getText();
        String pass = passTextFieldLoginPage.getText();
        Stage curStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        student = dbUtil.findStudent(userName, pass);

        if(student != null) {

            // get main stage
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("views/mainView.fxml"));
            Parent root = loader.load();

            // setup scene for main session
            Scene mainScene = new Scene(root);

            MainController mainController = new MainController();
            btnMainLogin.setText("LOGGED IN");
            mainController.recievedData(student, btnMainLogin);
            curStage.close();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication");
            alert.setContentText("Enter the correct password or username");
            Optional<ButtonType> action = alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    Button btnMainLogin;
    // receives data from other controllers
    public void receivedData(Stage mainStage, Button btnMainLogin) {
        this.mainStage = mainStage;
        this.btnMainLogin = btnMainLogin;
    }

    // handles event when the cancel button is clicked - reverts user back to the landing screen
    public void onClickCancel(ActionEvent event) throws IOException {
        Stage curStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        curStage.close();
    }

}
