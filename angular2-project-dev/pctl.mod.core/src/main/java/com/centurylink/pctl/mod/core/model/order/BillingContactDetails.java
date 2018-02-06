package com.centurylink.pctl.mod.core.model.order;

import java.io.Serializable;

/**
 * Created by veeraragavan.k on 28-11-2016.
 */
public class BillingContactDetails implements Serializable{

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String phoneNumberExtn;
    private String email;
    private String jobTitle;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberExtn() {
        return phoneNumberExtn;
    }

    public void setPhoneNumberExtn(String phoneNumberExtn) {
        this.phoneNumberExtn = phoneNumberExtn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
