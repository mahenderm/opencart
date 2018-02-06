package com.centurylink.pctl.mod.sfaint.modal;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 18-11-2016.
 */
@Component
public class CreateAccountRequest implements Serializable{

    private String accountname;
    private String accountType;
    private String legalEntityType;
    private String industry;
    private String accountSource;
    private String phone;
    private String email;
    private String streetaddline1;
    private String streetaddline2;
    private String city;
    private String state;
    private String zip;
    private String country;

    public CreateAccountRequest() {
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getaccountType() {
        return accountType;
    }

    public void setaccountType(String type) {
        this.accountType = type;
    }

    public String getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(String legalEntityType) {
        this.legalEntityType = legalEntityType;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(String accountSource) {
        this.accountSource = accountSource;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetaddline1() {
        return streetaddline1;
    }

    public void setStreetaddline1(String streetaddline1) {
        this.streetaddline1 = streetaddline1;
    }

    public String getStreetaddline2() {
        return streetaddline2;
    }

    public void setStreetaddline2(String streetaddline2) {
        this.streetaddline2 = streetaddline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "SfaintAccountRequest{" +
            "accountname='" + accountname + '\'' +
            ", accountType='" + accountType + '\'' +
            ", legalEntityType='" + legalEntityType + '\'' +
            ", industry='" + industry + '\'' +
            ", accountSource='" + accountSource + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", streetaddline1='" + streetaddline1 + '\'' +
            ", streetaddline2='" + streetaddline2 + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", country='" + country + '\'' +
            '}';
    }

}
