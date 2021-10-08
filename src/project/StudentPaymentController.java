package project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentPaymentController implements Initializable {

    public TextField txtFNameOnCard;
    public TextField txtLNameOnCard;

    public TextField txtCardNumber;
    public TextField txtCvcNumber;

    public Button btnPay;
    public Button btnCancel;

    double sessionCost = 150.0;
    DatabaseUtil dbUtil = new DatabaseUtil();

    // handles events when the payment button is clicked
    public void onClickedPay(Event event) throws SQLException, ClassNotFoundException, IOException {

        Stage curStage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        double funds = dbUtil.getFund(txtCardNumber.getText());

        // check if all field are filled
        if(!(txtFNameOnCard == null && txtLNameOnCard == null && txtCardNumber == null && txtCvcNumber == null)){

            // check if funds are sufficient in student bank account
            if(funds >= 0 && funds > numSessions*sessionCost){

                // update student account in the database
                dbUtil.updateFunds(txtCardNumber.getText(), funds - numSessions*sessionCost);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("TUTOR BUDDIE - PAYMENT PAGE");
                alert.setContentText("Session(s) are successfully booked");

                Optional<ButtonType> action = alert.showAndWait();

                // go back to profile view
                if(action.get() == ButtonType.OK){
                    curStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("views/studentProfileView.fxml"));
                    mainStage.setScene(new Scene(root,600,400));
                }
            }

            // warning message when there are insufficient funds in account
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("TUTOR BUDDIE - PAYMENT PAGE");
                alert.setContentText("Session(s) could not be booked due to insufficient funds");

                Optional<ButtonType> action = alert.showAndWait();
                // go back to profile view
                if (action.get() == ButtonType.OK) {
                    Parent root = FXMLLoader.load(getClass().getResource("views/studentProfileView.fxml"));
                    mainStage.setScene(new Scene(root, 600, 400));
                    curStage.close();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("TUTOR BUDDIE - PAYMENT PAGE");
            alert.setContentText("Fill all the required field(s)");
        }

    }

    public void onClickedCancel(ActionEvent event) {

    }

    Stage mainStage;
    int numSessions;

    public void receivedData(Stage mainStage, int numSess){
        this.mainStage = mainStage;
        numSessions = numSess;

        System.out.println("Data received number of sessions : " + numSessions);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
