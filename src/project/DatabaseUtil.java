package project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.model.Module;
import project.model.Student;
import java.sql.*;

public class DatabaseUtil {

    //region Connection object
    public Connection connection = null;
    //endregion

    public void connectToDB() throws SQLException, ClassNotFoundException {
        System.out.println("Connecting to DB....");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection("jdbc:ucanaccess://D://School//2021//WRRV301 - Project//S2//Implementation//HornetsProject1.accdb");
    }

    public boolean isConnected() throws SQLException, ClassNotFoundException {
        connectToDB();
        if (connection != null) {
            System.out.println("Connection is found!");
            return true;
        }
        return false;
    }

    public Student findStudent(String userName, String password) throws SQLException, ClassNotFoundException {

        if (isConnected()){

            System.out.println("is connected");
            System.out.println("Username = " + userName + " Password " + password);

            String sql = "SELECT * FROM Student WHERE emailAddress = '"+userName+"' AND password = '"+password+"' ";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {

                String fName = res.getString("fName");
                String lName = res.getString("lName");
                String emailAddress = res.getString("emailAddress");
                String street = res.getString("streetName");
                String city = res.getString("city");
                String suburb = res.getString("suburb");
                int pCode = res.getInt("postalCode");

                Student student =   new Student(new SimpleStringProperty(fName), new SimpleStringProperty(lName),
                                    new SimpleStringProperty(emailAddress), new SimpleStringProperty(street),
                                    new SimpleStringProperty(city), new SimpleStringProperty(suburb),
                                    new SimpleIntegerProperty(pCode));

                student.setStudentID(res.getInt("studentID"));
                return student;
            }

            /*String sql = "select * from Student where email = '"+userName+"' and password = '"+password+"' ";

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println(resultSet.getFetchSize());

            while (resultSet.next()) {
                System.out.println("sql found...");
                String curName = resultSet.getString("fName");
                String curSurname = resultSet.getNString("lName");
                String curEmail = resultSet.getString("email");
                String contactNumber = resultSet.getString("contactNumber");
                String street = resultSet.getString("streetName");
                String suburb = resultSet.getString("suburb");
                String city = resultSet.getString("city");
                String pCode = resultSet.getString("pCode");
                System.out.println("Street name " + street);

                return new Student(new SimpleStringProperty(curName), new SimpleStringProperty(curSurname), new SimpleStringProperty(curEmail),
                        new SimpleStringProperty(contactNumber), new SimpleStringProperty(street), new SimpleStringProperty(city),
                        new SimpleStringProperty(suburb), new SimpleStringProperty(pCode));
            }*/
        }
        return null;
    }

    public Module findModule(int moduleID) throws SQLException, ClassNotFoundException {

        if (isConnected()){

            String sql = "SELECT * FROM Module WHERE moduleID = '"+moduleID+"' ";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {

                String moduleCode = res.getString("moduleCode");
                String universityName = res.getString("universityName");
                String term = res.getString("term");
                String yearLevel = res.getString("yearLevel");
                String moduleName = res.getString("moduleName");

                Module module = new Module(moduleCode, universityName, term, yearLevel, moduleName);
                module.setModuleID(res.getInt("moduleID"));
                return module;
            }
            /*String sql = "select * from Student where email = '"+userName+"' and password = '"+password+"' ";

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println(resultSet.getFetchSize());

            while (resultSet.next()) {
                System.out.println("sql found...");
                String curName = resultSet.getString("fName");
                String curSurname = resultSet.getNString("lName");
                String curEmail = resultSet.getString("email");
                String contactNumber = resultSet.getString("contactNumber");
                String street = resultSet.getString("streetName");
                String suburb = resultSet.getString("suburb");
                String city = resultSet.getString("city");
                String pCode = resultSet.getString("pCode");
                System.out.println("Street name " + street);

                return new Student(new SimpleStringProperty(curName), new SimpleStringProperty(curSurname), new SimpleStringProperty(curEmail),
                        new SimpleStringProperty(contactNumber), new SimpleStringProperty(street), new SimpleStringProperty(city),
                        new SimpleStringProperty(suburb), new SimpleStringProperty(pCode));
            }*/
        }
        return null;
    }

    public ObservableList getAllModules() throws SQLException, ClassNotFoundException {

        ObservableList<Module> modules = FXCollections.observableArrayList();

        connectToDB();

        if(isConnected()){

            String sql = "SELECT * FROM Module";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()){

                String moduleCode = res.getString("moduleCode");
                String universityName = res.getString("universityName");
                String term = res.getString("term");
                String yearLevel = res.getString("yearLevel");
                String moduleName = res.getString("moduleName");

                Module module = new Module(moduleCode, universityName, term, yearLevel, moduleName);
                modules.add(module);
            }
            return modules;
        }
        return null;
    }

    public void enrollModule(int moduleID, int studentID) throws SQLException, ClassNotFoundException {

        connectToDB();

        if (isConnected()){

            String sql = "insert into Enrolment(moduleID, studentID) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, moduleID);
            statement.setInt(2, studentID);
            statement.executeUpdate();
        }
    }

    public int getModuleID() throws SQLException, ClassNotFoundException {

        connectToDB();
        if(isConnected()){

        }
        return -1;
    }
}
