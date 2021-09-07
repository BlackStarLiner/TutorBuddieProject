package project.model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty street;
    private SimpleStringProperty city;
    private SimpleStringProperty suburb;
    private SimpleIntegerProperty pCode;

    public Student(SimpleStringProperty firstName, SimpleStringProperty lastName,
                   SimpleStringProperty email, SimpleStringProperty street,
                   SimpleStringProperty city, SimpleStringProperty suburb,
                   SimpleIntegerProperty pCode) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.suburb = suburb;
        this.pCode = pCode;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getSuburb() {
        return suburb.get();
    }

    public SimpleStringProperty suburbProperty() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb.set(suburb);
    }

    public int getpCode() {
        return pCode.get();
    }

    public SimpleIntegerProperty pCodeProperty() {
        return pCode;
    }

    public void setpCode(int pCode) {
        this.pCode.set(pCode);
    }
}
