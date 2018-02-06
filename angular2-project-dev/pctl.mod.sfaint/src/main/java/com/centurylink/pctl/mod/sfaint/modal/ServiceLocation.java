package com.centurylink.pctl.mod.sfaint.modal;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 23-11-2016.
 */
public class ServiceLocation  implements Serializable {
    private String serviceLocationName;
    private String serviceLocNPA;
    private String serviceLocNXX;
    private String serviceLocSWCCLLICODE;
    private String serviceLocLongtitude;
    private String serviceLocLatitude;
    private String serviceLocStreetAddressLine1;
    private String serviceLocStreetAddressLine2;
    private String serviceLocCity;
    private String serviceLocState;
    private String serviceLocZip;
    private String serviceLocCountry;


    public ServiceLocation(String serviceLocationName,
    String serviceLocNPA,
    String serviceLocNXX,
    String serviceLocSWCCLLICODE,
    String serviceLocLongtitude,
    String serviceLocLatitude,
    String serviceLocStreetAddressLine1,
    String serviceLocStreetAddressLine2,
    String serviceLocCity,
    String serviceLocState,
    String serviceLocZip,
    String serviceLocCountry){
        this.serviceLocationName = serviceLocationName;
        this.serviceLocNPA = serviceLocNPA;
        this.serviceLocNXX =serviceLocNXX;
        this.serviceLocSWCCLLICODE =serviceLocSWCCLLICODE;
        this.serviceLocLongtitude =serviceLocLongtitude;
        this.serviceLocLatitude =serviceLocLatitude;
        this.serviceLocStreetAddressLine1 =serviceLocStreetAddressLine1;
        this.serviceLocStreetAddressLine2 =serviceLocStreetAddressLine2;
        this.serviceLocCity =serviceLocCity;
        this.serviceLocState =serviceLocState;
        this.serviceLocZip =serviceLocZip;
        this.serviceLocCountry =serviceLocCountry;
    }

    public String getServiceLocationName() {
        return serviceLocationName;
    }

    public void setServiceLocationName(String serviceLocationName) {
        this.serviceLocationName = serviceLocationName;
    }

    public String getServiceLocNPA() {
        return serviceLocNPA;
    }

    public void setServiceLocNPA(String serviceLocNPA) {
        this.serviceLocNPA = serviceLocNPA;
    }

    public String getServiceLocNXX() {
        return serviceLocNXX;
    }

    public void setServiceLocNXX(String serviceLocNXX) {
        this.serviceLocNXX = serviceLocNXX;
    }

    public String getServiceLocSWCCLLICODE() {
        return serviceLocSWCCLLICODE;
    }

    public void setServiceLocSWCCLLICODE(String serviceLocSWCCLLICODE) {
        this.serviceLocSWCCLLICODE = serviceLocSWCCLLICODE;
    }

    public String getServiceLocLongtitude() {
        return serviceLocLongtitude;
    }

    public void setServiceLocLongtitude(String serviceLocLongtitude) {
        this.serviceLocLongtitude = serviceLocLongtitude;
    }

    public String getServiceLocLatitude() {
        return serviceLocLatitude;
    }

    public void setServiceLocLatitude(String serviceLocLatitude) {
        this.serviceLocLatitude = serviceLocLatitude;
    }

    public String getServiceLocStreetAddressLine1() {
        return serviceLocStreetAddressLine1;
    }

    public void setServiceLocStreetAddressLine1(String serviceLocStreetAddressLine1) {
        this.serviceLocStreetAddressLine1 = serviceLocStreetAddressLine1;
    }

    public String getServiceLocStreetAddressLine2() {
        return serviceLocStreetAddressLine2;
    }

    public void setServiceLocStreetAddressLine2(String serviceLocStreetAddressLine2) {
        this.serviceLocStreetAddressLine2 = serviceLocStreetAddressLine2;
    }

    public String getServiceLocCity() {
        return serviceLocCity;
    }

    public void setServiceLocCity(String serviceLocCity) {
        this.serviceLocCity = serviceLocCity;
    }

    public String getServiceLocState() {
        return serviceLocState;
    }

    public void setServiceLocState(String serviceLocState) {
        this.serviceLocState = serviceLocState;
    }

    public String getServiceLocZip() {
        return serviceLocZip;
    }

    public void setServiceLocZip(String serviceLocZip) {
        this.serviceLocZip = serviceLocZip;
    }

    public String getServiceLocCountry() {
        return serviceLocCountry;
    }

    public void setServiceLocCountry(String serviceLocCountry) {
        this.serviceLocCountry = serviceLocCountry;
    }

    @Override
    public String toString() {
        return "ServiceLocation{" +
            "serviceLocationName='" + serviceLocationName + '\'' +
            ", serviceLocNPA='" + serviceLocNPA + '\'' +
            ", serviceLocNXX='" + serviceLocNXX + '\'' +
            ", serviceLocSWCCLLICODE='" + serviceLocSWCCLLICODE + '\'' +
            ", serviceLocLongtitude='" + serviceLocLongtitude + '\'' +
            ", serviceLocLatitude='" + serviceLocLatitude + '\'' +
            ", serviceLocStreetAddressLine1='" + serviceLocStreetAddressLine1 + '\'' +
            ", serviceLocStreetAddressLine2='" + serviceLocStreetAddressLine2 + '\'' +
            ", serviceLocCity='" + serviceLocCity + '\'' +
            ", serviceLocState='" + serviceLocState + '\'' +
            ", serviceLocZip='" + serviceLocZip + '\'' +
            ", serviceLocCountry='" + serviceLocCountry + '\'' +
            '}';
    }
}
