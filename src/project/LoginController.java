package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.model.Student;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

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

    public void onClickLoginBtnLogPage(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (dbUtil.isConnected()) {
            Parent root = FXMLLoader.load(getClass().getResource("views/loginView.fxml"));
            mainStage = (Stage) loginButton.getScene().getWindow();
            mainStage.setScene(new Scene(root, 600, 600));
        }

    }

    public void onClickLoginButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String userName = userNameTextFieldLoginPage.getText();
        String pass = passTextFieldLoginPage.getText();

        student = dbUtil.findStudent(userName, pass);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/MainController.fxml"));
       // Parent root = loader.load();

        MainController mainController = loader.getController();

        if(student != null) {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.hide();
            mainController.student = student;
            System.out.println(student.getpCode());
        }
        else
            System.out.println("User not found");
    }

    private void switchToMain(Button buttonClicked) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
        mainStage = (Stage)buttonClicked.getScene().getWindow();
        mainStage.setScene(new Scene(root,1000,600));
    }
}
