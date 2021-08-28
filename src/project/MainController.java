package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MainController {

    public Button requestButton;
    public TextField fNameTxtField;
    public TextField emailTxtField;
    public TextField streetNameTxtField;
    public TextField pCodeTxtField;
    public TextField lNameTxtField;
    public TextField suburbTxtField;
    public TextField cityTxtField;
    public PasswordField passTxtField;
    public PasswordField passReEnteredTxtField;
    public Button registerBtn;
    public Button cancelBtn;
    public Stage mainStage;


    Connection connection = null;

    @FXML
    private void actionOnClickReqButton() throws Exception { ;
        Parent root = FXMLLoader.load(getClass().getResource("views/registerView.fxml"));
        mainStage = (Stage)requestButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,600,600));
    }

    public void connectToDB() throws SQLException, ClassNotFoundException {

        System.out.println("Connecting to DB....");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection("jdbc:ucanaccess://D://School//2021//WRRV301 - Project//S2//Implementation//HornetsProjectDB.accdb");

        String fName = "Masivuye"; String lName = "Mpako"; String email = "mmasivuye@gmail.com";
        String pass = "lpkpojp"; String startDate = null;
        String streetName = "45 Loch Street"; String suburb = "North End";
        String city = "Makhanda"; int pCode = 5060;


        if (isConnected()) {

            System.out.println("Connection to DB is found!");

            Statement statement = connection.createStatement();

            String sql = "insert into Student values('1', '"+fName+"', '"+lName+"', '"+pass+"', '"+email+"'," +
                                                    " '"+startDate+"', '"+startDate+"', '"+streetName+"'," +
                                                    "'"+suburb+"', '"+city+"', '"+pCode+"')";

            statement.execute(sql);
            System.out.println("record successfully added");
        }

    }

    @FXML
    public void onRegisterClicked() throws SQLException, ClassNotFoundException, IOException {

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


        if(isConnected()) {
            System.out.println("Connection to DB is found!");

            Statement statement = connection.createStatement();
            String sql = "insert into Student values('1', '"+fName+"', '"+lName+"', '"+pass+"', '"+email+"'," +
                    " '"+startDate+"', '"+streetName+"'," +
                    "'"+suburb+"', '"+city+"', '"+pCode+"', '"+null+"')";
            statement.execute(sql);
            System.out.println("record successfully added");
        }
        else
            System.out.println("lost the connection");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Information");
        alert.setContentText("To confirm registration open your emails");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.CLOSE){
            Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
            mainStage = (Stage)registerBtn.getScene().getWindow();
            mainStage.setScene(new Scene(root,1000,600));
        }
    }

    public void onCancelClicked() throws IOException {

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

    private Boolean isConnected(){
        return connection != null;
    }

 }

