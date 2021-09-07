package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class StudentRegistrationController {

    public Stage mainStage;

    public TextField fNameTxtField;
    public TextField emailTxtField;
    public TextField streetNameTxtField;
    public TextField pCodeTxtField;
    public TextField lNameTxtField;
    public TextField cityTxtField;
    public TextField suburbTxtField;
    public PasswordField passTxtField;
    public PasswordField passReEnteredTxtField;
    public Button registerBtn;
    public Button cancelBtn;

    //region Connection object
    Connection connection = null;
    Statement statement = null;
    //endregion

    public void onRegisterClicked(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        connectToDB();

        String fName = fNameTxtField.getText();
        String lName = lNameTxtField.getText();
        String email = emailTxtField.getText();
        LocalDate today = LocalDate.now();
        String startDate = null;
        String streetName = streetNameTxtField.getText();
        String suburb = suburbTxtField.getText();
        String city = cityTxtField.getText();
        int pCode = Integer.parseInt(pCodeTxtField.getText());
        String pass = passTxtField.getText();


        if(connection != null) {
            System.out.println("Connection to DB is found!");

            Statement statement = connection.createStatement();
            String sql = "insert into Student values('1', '"+fName+"', '"+lName+"', '"+pass+"', '"+email+"'," +
                    " '"+startDate+"', '"+streetName+"'," +
                    "'"+suburb+"', '"+city+"', '"+pCode+"', '"+null+"', '"+null+"')";
            statement.execute(sql);
            System.out.println("record successfully added");
        }

        else
            System.out.println("lost the connection");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Information");
        alert.setContentText("Congratulations welcome to Tutor Buddie, you can now add your modules");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.CLOSE){
            Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
            mainStage = (Stage)registerBtn.getScene().getWindow();
            mainStage.setScene(new Scene(root,1000,600));
        }
    }

    public void onCancelClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("A you sure you want to cancel?");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
            mainStage = (Stage)cancelBtn.getScene().getWindow();
            mainStage.setScene(new Scene(root,1000,600));
        }

    }

    public void connectToDB() throws SQLException, ClassNotFoundException {
        System.out.println("Connecting to DB....");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection("jdbc:ucanaccess://D://School//2021//WRRV301 - Project//S2//Implementation//HornetsProjectDB.accdb");
        statement = connection.createStatement();
    }

}
