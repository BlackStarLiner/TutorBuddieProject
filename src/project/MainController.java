package project;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project.model.Student;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.FileHandler;

public class MainController {

    //region Cached values
    public Button requestTutButton;
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
    public ImageView imageViewMain;
    public Label labelUserName;
    public Label labelPassword;
    public Button logPageButton;
    public Button cancelPageButton;
    public Button loginButton;
    public Button titleButton;
    public TextField userNameTextFieldLoginPage;
    public PasswordField passTextFieldLoginPage;

    public Label fNameLabel;
    public Label lNameLabel;
    public Label universityLabel;
    public Label contactNumberLabel;
    public Label streetLabel;
    public Label suburbLabel;
    public Label pCodeLabel;
    public Label cityLabel;
    Student student;


    //endregion

    //region Connection object
    Connection connection = null;
    Statement statement = null;
    //endregion

    @FXML
    private void actionOnClickReqButton() throws Exception { ;
        Parent root = FXMLLoader.load(getClass().getResource("views/registerView.fxml"));
        mainStage = (Stage)requestTutButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,600,600));
    }

    public void connectToDB() throws SQLException, ClassNotFoundException {

        System.out.println("Connecting to DB....");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection("jdbc:ucanaccess://D://School//2021//WRRV301 - Project//S2//Implementation//HornetsProjectDB.accdb");
        statement = connection.createStatement();
/*        String fName = "Masivuye"; String lName = "Mpako"; String email = "mmasivuye@gmail.com";
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
        }*/
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
        alert.setContentText("Congratulations welcome to Tutor Buddie, you can now add your modules");
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
    @FXML
    public void setImageViewMain(){
        imageViewMain.setImage(new Image("res/images/tutorBooks.png"));
    }

    // Query Student table
    public void onClickedLoginButton() throws SQLException, ClassNotFoundException, IOException {

        connectToDB();

        Parent root = FXMLLoader.load(getClass().getResource("views/loginView.fxml"));
        mainStage = (Stage) loginButton.getScene().getWindow();
        mainStage.setScene(new Scene(root, 600, 600));
    }

    public void onClickLoginBtnLogPage() throws SQLException, ClassNotFoundException, IOException {
        connectToDB();
        System.out.println(userNameTextFieldLoginPage.getText());
        String sql = "select * from Student where email = '"+userNameTextFieldLoginPage.getText()+"' and password = '"+passTextFieldLoginPage.getText()+"' ";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            System.out.println("First name: " + resultSet.getString("fName") + " Surname: " + resultSet.getString("lName"));

            String curName = resultSet.getString("fName");
            String curSurname = resultSet.getNString("lName");
            String curEmail = resultSet.getString("email");
            String contactNumber = resultSet.getString("contactNumber");
            String street = resultSet.getString("streetName");
            String suburb = resultSet.getString("suburb");;
            String city = resultSet.getString("city");
            String pCode = resultSet.getString("pCode");;

            student = new Student(new SimpleStringProperty(curName), new SimpleStringProperty(curSurname), new SimpleStringProperty(curEmail),
                      new SimpleStringProperty(contactNumber), new SimpleStringProperty(street), new SimpleStringProperty(city),
                      new SimpleStringProperty(suburb), new SimpleStringProperty(pCode));
            switchToMain(logPageButton);
        }
    }

    private void switchToMain(Button buttonClicked) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
        mainStage = (Stage)buttonClicked.getScene().getWindow();
        mainStage.setScene(new Scene(root,1000,600));
    }

    public void disconnectDB() {
        System.out.println("Disconnecting from database...");
        try {
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("   Unable to disconnect from database");
        }
    }

    public void onClickedProfileButton() {
        fNameLabel.setText(student.getFirstName());
        lNameLabel.setText(student.getLastName());
        labe
    }
}

