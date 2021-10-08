package project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.model.Module;
import project.model.Session;
import project.model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class SessionController implements Initializable {

    //region Cache values
    public DatePicker datePickerAddSess;
    public ChoiceBox comboBoxStartTimesAddSess;
    public ChoiceBox comboBoxEndTimesAddSess;
    public RadioButton radioBtnF2F;
    public RadioButton radioBtnVirtual;
    public ToggleGroup sessionTypeToggleGroup;
    public Button btnViewProfileAddSess;
    public Button btnSessionsAddSess;
    public Button btnViewModulesAddSess;
    public Button btnSaveAddSess;
    public Button btnMakePaymentAddSess;
    public Button btnCancelAddSess;

    ObservableList<String> sessions;
    Stage mainStage;
    int indexStartTime;
    int indexEndTime;
    LocalDate selectedDate;
    Student student;
    Module module;
    String startTime;
    String endTime;
    DatabaseUtil dbUtil = new DatabaseUtil();
    //endregion

    // Event handler for VIEW PROFILE BUTTON
    public void onClickViewProfile(ActionEvent event) throws IOException {
        mainStage = (Stage)btnViewProfileAddSess.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/studentProfileView.fxml"));
        mainStage.setScene(new Scene(root,600,400));
    }

    // Event handler for SESSION BUTTON
    public void onClickSessions(ActionEvent event) throws IOException {
        mainStage = (Stage)btnSessionsAddSess.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/sessionListView.fxml"));
        mainStage.setScene(new Scene(root,600,400));
    }

    // Event handler for MODULES BUTTON
    public void onClickViewModules(ActionEvent event) throws IOException {
        mainStage = (Stage)btnViewModulesAddSess.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("views/studentModuleListView.fxml"));
        mainStage.setScene(new Scene(root,600,400));
    }

    // Saves the sessions selected
    public void onClickSaveBtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        RadioButton selectedRButton = (RadioButton)sessionTypeToggleGroup.getSelectedToggle();
        String date = datePickerAddSess.getValue().toString();

        startTime = comboBoxStartTimesAddSess.getValue().toString();
        endTime = comboBoxEndTimesAddSess.getValue().toString();

        String sessionType = selectedRButton.getText();

        if(sessionType != null && date != null && startTime != null && endTime != null){
            Session session = new Session(startTime, endTime, date, sessionType, 1, student.getStudentID());
            dbUtil.addSession(session);
        }

        else{
           setUpAlertDialog("Session Select", "Select all the fields required");
        }
    }

    // handles action on clicking the makePayment button
    public void onClickMakePayment(ActionEvent event) throws IOException {

        // get main stage
        mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("views/paymentView.fxml"));
        Parent root = loader.load();

        // setup scene for adding session
        Scene sessionScene = new Scene(root);

        // pass data to the student payment controller
        StudentPaymentController studentPaymentController = loader.getController();
        studentPaymentController.receivedData(mainStage, getNumSessions(startTime, endTime));

        // setup stage for the payment view
        Stage loginStage = new Stage();
        loginStage.setTitle("Tutor Buddie - PAYMENT PAGE");
        loginStage.initOwner(mainStage);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(sessionScene);
        loginStage.show();
    }

    public void onClickCancel(ActionEvent event) {}

    // sets up session intervals
    private void setSessions(){

        for (int i = 1; i <10; i++) {
            if((i + 7) < 10)
                sessions.add("0"+(i+7)+":00");
            else
                sessions.add((i+7)+":00");
        }
        comboBoxStartTimesAddSess.setItems(sessions);
        comboBoxEndTimesAddSess.setItems(sessions);
    }

    // date selection and validation (cannot choose back date)
    public void onDateSelection(ActionEvent event) {
        selectedDate = datePickerAddSess.getValue();
        LocalDate today = LocalDate.now();
        if(today.isAfter(selectedDate)) {
            Optional<ButtonType> action = setUpAlertDialog("Date Selector", "Date chosen is INVALID");
            if (action.get() == ButtonType.OK)
                selectedDate = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sessions = FXCollections.observableArrayList();
        setSessions();

        comboBoxStartTimesAddSess.setOnAction(event -> {
            indexStartTime = comboBoxStartTimesAddSess.
                    getSelectionModel().getSelectedIndex();
        });
        comboBoxEndTimesAddSess.setOnAction(event -> {
            indexEndTime = comboBoxEndTimesAddSess.
                    getSelectionModel().getSelectedIndex();
            if(indexEndTime <= indexStartTime){
                Optional<ButtonType> action = setUpAlertDialog("Sessions Dialog","Invalid session selected");
                if(action.get()==ButtonType.OK)
                    indexEndTime = 0;
            }
        });

        student =   new Student(new SimpleIntegerProperty(1), new SimpleStringProperty("Philasande"), new SimpleStringProperty("Tono"),
                new SimpleStringProperty("philasande@gmail.com"), new SimpleStringProperty("University Way"),
                new SimpleStringProperty("Gqeberha"), new SimpleStringProperty("Summerstrand"),
                new SimpleIntegerProperty(6001));

        module = new Module("JHA301","Wits","1","3","Business Entities", 1);
    }

    // returns button type of the alert invoked
    private Optional<ButtonType> setUpAlertDialog(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(content);

        return alert.showAndWait();
    }

    // calculates and returns number of hours for sessions selected
    private int getNumSessions(String startTime, String endTime) {
        int numSessions = Math.abs((Integer.parseInt(startTime.substring(0,2))-(Integer.parseInt(endTime.substring(0,2)))));
        return numSessions;
    }
}
