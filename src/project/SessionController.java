package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.model.Session;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class SessionController implements Initializable {

    public DatePicker datePickerAddSess;

    public ChoiceBox comboBoxStartTimesAddSess;
    public ChoiceBox comboBoxEndTimesAddSess;

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

    public void onClickSaveBtn(ActionEvent event) {

    }

    public void onClickMakePayment(ActionEvent event) {}

    public void onClickCancel(ActionEvent event) {}

    private void setSessions(){

        for (int i = 1; i <9; i++) {
            if((i + 7) < 10)
                sessions.add("0"+(i+7)+":00");
            else
                sessions.add((i+7)+":00");
        }
        comboBoxStartTimesAddSess.setItems(sessions);
        comboBoxEndTimesAddSess.setItems(sessions);
    }

    public void onDateSelection(ActionEvent event) {

        selectedDate = datePickerAddSess.getValue();
        LocalDate today = LocalDate.now();

        if(today.isAfter(selectedDate)) {
/*
            String header = "Date Selected";
            String content = "Date chosen is INVALID";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Date Selected");
            alert.setContentText("Date chosen is INVALID");*/
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
    }

    private Optional<ButtonType> setUpAlertDialog(String header, String content){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(header);
        alert.setContentText(content);

        return alert.showAndWait();
    }
}
