package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class StudentPaymentController {

    public TextField txtFNameOnCard;
    public TextField txtLNameOnCard;

    public TextField txtCardNumber;
    public TextField txtCvcNumber;

    public Button btnPay;
    public Button btnCancel;

    double sessionCost = 150.0;
    int numberOfSessions;

    DatabaseUtil dbUtil = new DatabaseUtil();

    Stage mainStage;

    public void onClickedPay() throws SQLException, ClassNotFoundException, IOException {

        double funds = dbUtil.getFund(txtCardNumber.getText());
        System.out.println("funds  = R" + funds);
        if(funds >= 0 && funds > numberOfSessions*sessionCost){
            System.out.println("Session cost" + numberOfSessions*sessionCost);
            // update student account in the database
            dbUtil.updateFunds(txtCardNumber.getText(), funds - numberOfSessions*sessionCost);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PAYMENT PAGE");
            alert.setContentText("Session(s) are successfully");

            Optional<ButtonType> action = alert.showAndWait();

            // go back to profile view
            if(action.get() == ButtonType.OK){
                //mainStage = (Stage)btnViewProfileAddSess.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("views/studentProfileView.fxml"));
                mainStage.setScene(new Scene(root,600,400));
            }
        }
    }

    public void onClickedCancel(ActionEvent event) {
    }

    public void receivedData(Stage mainStage, int numSessions){
        this.mainStage = mainStage;
        numberOfSessions = numSessions;
    }

}
