/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *Record
 * The rentals Record Object class.
 * @author Faith Kilonzi.
 */
public class Record {

    public StringProperty name = new SimpleStringProperty();
    public StringProperty address = new SimpleStringProperty();
    public StringProperty chairs = new SimpleStringProperty();
    public StringProperty dateEntry = new SimpleStringProperty();
    public StringProperty tables = new SimpleStringProperty();
    public StringProperty phone = new SimpleStringProperty();
    public StringProperty canopies = new SimpleStringProperty();

   
    /**
     * Getter and setter methods for the member attributes of the record.
     * @return
     */
    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getTables() {
        return tables.get();
    }

    public void setTables(String value) {
        tables.set(value);
    }

    public StringProperty tablesProperty() {
        return tables;
    }

    public String getDateEntry() {
        return dateEntry.get();
    }

    public void setDateEntry(String value) {
        dateEntry.set(value);
    }

    public StringProperty dateEntryProperty() {
        return dateEntry;
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public String getCanopies() {
        return canopies.get();
    }

    public void setCanopies(String value) {
        canopies.set(value);
    }

    public StringProperty canopiesProperty() {
        return canopies;
    }

    public String getChairs() {
        return chairs.get();
    }

    public void setChairs(String value) {
        chairs.set(value);
    }

    public StringProperty chairsProperty() {
        return chairs;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

}
