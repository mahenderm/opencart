package com.centurylink.pctl.mod.core.model.cart;

import java.io.Serializable;

/**
 * ServiceContact Bean
 *
 * @author nagavenkatakirang
 */
public class ServiceContact implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * email of ServiceContact
     */
    private String email;
    /**
     * firstName of ServiceContact
     */
    private String firstName;
    /**
     * lastName of ServiceContact
     */
    private String lastName;
    /**
     * phoneNumber of ServiceContact
     */
    private String phoneNumber;
    //constructors
    public ServiceContact() {}

    public ServiceContact(String email, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    //getters and setters
    /**
     * getter for email
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * setter for email
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * getter for firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * setter for firstName
     * @param firstName first Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * getter for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * setter for lastName
     * @param lastName last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * getter for phoneNumber
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * setter for phoneNumber
     * @param phoneNumber phone Number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * toString() method is overridden to return the state of the ServiceContact
     */
    @Override
    public String toString() {
        return "ServiceContact{" +
            "email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
    }
}
