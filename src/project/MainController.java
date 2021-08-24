package project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


    Connection connection = null;
    Statement statement = null;

    public MainController() throws SQLException, ClassNotFoundException {
        connectToDB();
    }

    private void actionOnClickReqButton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/registerView.fxml"));
        Stage mainStage = (Stage)requestButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,600,600));
        mainStage.show();

    }


    private void addNewStudent() throws SQLException {

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();

        if(connection != null) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("insert into Student + (fName, lName, emailAddress, startDate, streetName, " +
                            "suburb, city, postalCode, cardNumber, cardType, cvcCode," + "expiryDate, profileImage, password)" +
                            "values(fNameTxtField.getText(), lNameTxtField.getText(), emailTxtField.getText(),
                            date.format(now), streetNameTxtField.getText(), suburbTxtField.getText(), cityTxtField.getText(), pCodeTxtField.getText(), null, null, null, null, null, passTxtField.getText()))"

        }
    }

    public void connectToDB() throws SQLException, ClassNotFoundException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection("jdbc:ucanaccess://D://School//2021//WRRV301 - Project//S2//Implementation//HornetsProject1.accdb");
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Student");
        while (resultSet.next())
            System.out.println(resultSet.getString(2));
        connection.close();
    }
}
