package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import project.model.Module;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddModuleController implements Initializable {

    public ComboBox moduleComboBox;
    public Button saveModuleButton;
    public Button addSessionButton;

    Stage mainStage;

    String selectedModule;

    DatabaseUtil dbUtil = new DatabaseUtil();
    ObservableList<Module> modules;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            modules = dbUtil.getAllModules();
            ObservableList<String> moduleNames = FXCollections.observableArrayList();

            for (int i = 0; i < modules.size(); i++)
                moduleNames.add(modules.get(i).getModuleName());
            moduleComboBox.setItems(moduleNames);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        moduleComboBox.setOnAction(event -> {
            selectedModule = (String) moduleComboBox.getSelectionModel().getSelectedItem();
        });
    }

    public void onClickSaveModuleButton(ActionEvent event) throws SQLException, ClassNotFoundException {

        mainStage = (Stage)saveModuleButton.getScene().getWindow();

        for (int i = 0; i < modules.size(); i++) {
            Module curModule = modules.get(i);
            if(selectedModule.equals(curModule.getModuleName())){
                dbUtil.enrollModule(1,curModule.getModuleID());
                return;
            }
        }
    }

    public void onClickAddSessionButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("views/sessionsView.fxml"));
        mainStage = (Stage)saveModuleButton.getScene().getWindow();
        mainStage.setScene(new Scene(root,700,380));
    }

    public void onClickSelectModule(ActionEvent event) {
    }
}
