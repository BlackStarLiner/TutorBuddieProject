package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    //region Cached values
    public Button requestTutButton;

    public Stage mainStage;
    public ImageView imageViewMain;

    public Button loginButton;
    public Button titleButton;

    public Button profileButton;
    public Button mainLoginButton;

    public Student student;
    public Label textLabelMain;
    //endregion

    //region Connection object
    Connection connection = null;
    Statement statement = null;
    //endregion

    /*private void initialiseData(){

        fNameLabel.setText(student.getFirstName());
        lNameLabel.setText(student.getLastName());
        emailLabel.setText(student.getEmail());

        System.out.println(student.getFirstName());
        streetLabel.setText(student.getStreet());
        suburbLabel.setText(student.getSuburb());
        cityLabel.setText(student.getCity());
        pCodeLabel.setText(student.getpCode());

    }*/

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
    }

/*
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
*/

/*    public void onCancelClicked() throws IOException {

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

    }*/

    // Query Student table
    public void onClickedLoginButton() throws SQLException, ClassNotFoundException, IOException {
        connectToDB();
        Parent root = FXMLLoader.load(getClass().getResource("views/loginView.fxml"));
        mainStage = (Stage) loginButton.getScene().getWindow();
        mainStage.setScene(new Scene(root, 600, 600));
    }
    /*public void onClickLoginBtnLogPage() throws SQLException, ClassNotFoundException, IOException {
        connectToDB();
        System.out.println(userNameTextFieldLoginPage.getText());
        String sql = "select * from Student where email = '"+userNameTextFieldLoginPage.getText()+"' and password = '"+passTextFieldLoginPage.getText()+"' ";
        ResultSet resultSet = statement.executeQuery(sql);

        if(connection != null) {

            while (resultSet.next()) {

                System.out.println("First name: " + resultSet.getString("fName") + " Surname: " + resultSet.getString("lName"));
                System.out.println(resultSet.getString("suburb"));
                String curName = resultSet.getString("fName");
                String curSurname = resultSet.getNString("lName");
                String curEmail = resultSet.getString("email");
                String contactNumber = resultSet.getString("contactNumber");
                String street = resultSet.getString("streetName");
                String suburb = resultSet.getString("suburb");
                String city = resultSet.getString("city");
                String pCode = resultSet.getString("pCode");


                student = new Student(new SimpleStringProperty(curName), new SimpleStringProperty(curSurname), new SimpleStringProperty(curEmail),
                        new SimpleStringProperty(contactNumber), new SimpleStringProperty(street), new SimpleStringProperty(city),
                        new SimpleStringProperty(suburb), new SimpleStringProperty(pCode));

                System.out.println("First name: " + student.getFirstName() + " Surname: " + student.getLastName() + " from student object");
                switchToMain(logPageButton);
            }
        }
    }*/
    private void switchToMain(Button buttonClicked) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/mainView.fxml"));
        mainStage = (Stage)buttonClicked.getScene().getWindow();
        mainStage.setScene(new Scene(root,1000,600));
    }

    public void onClickedProfileButton() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("views/studentProfileView.fxml"));
/*        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/studentProfileView.fxml"));
        Parent root = loader.load();

        StudentProfileController studentProfileController = loader.getController();
        studentProfileController.setInformation(student);*/
        mainStage = (Stage)profileButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,700,600));
    }

    @FXML
    public void onClickedMainLoginButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("views/loginView.fxml"));
        mainStage = (Stage)mainLoginButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,1000,600));

        /*LoginController loginSceneController = loader.getController();

        mainStage = (Stage)mainLoginButton.getScene().getWindow();
        mainStage.setScene(new Scene(root, 500, 500));

        Stage loginStage = new Stage();
        loginStage.setTitle("Tutor Buddie - Login");
        loginStage.initOwner(mainStage);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.setScene(new Scene(root, 300, 300));
        loginStage.show();*/
    }

    public void setStudentInformation(Student student){
        this.student = student;
        textLabelMain.setText(student.getFirstName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

