package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;


public class Main extends Application {

    static Connection conn = null;
    static Statement statement = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("landing.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public Scene landingScene(){

        Button reqTutButton = new Button("REQUEST TUTOR");
        reqTutButton.setId("#reqTutButton");
        Button becomeTutButton = new Button("BECOME A TUTOR");
        becomeTutButton.setId("#becomeTutButton");

        GridPane root = new GridPane();



    }

    public static void main(String[] args) {

        launch(args);
        //connectToDB();
        if (conn!=null)
            System.out.println("ghfjk");

    }

  /*  public static void connectToDB() {
        Connection conn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:sqlserver://localhost\\MSSQLSERVER:1434;database=project";
            //String user = "Windows authentication";
            //String pass = "4296";
            conn = DriverManager.getConnection(dbURL*//*, user, pass*//*);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
*/

}
