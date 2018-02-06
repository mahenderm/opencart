package com.centurylink.pctl.mod.address.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by haribabu.ka on 04-12-2016.
 */
public class CivicAddressValidationResult extends  AddressManagementResponse{


    @XStreamAlias("Result")
    protected ResultColor result;
    @XStreamAlias("CivicAddresses")
    protected ArrayOfCivicAddress civicAddresses;
    @XStreamAlias("GeoPoint")
    protected GeoPoint geoPoint;
    @XStreamAlias("CensusMatchLevel")
    protected String censusMatchLevel;

    @XStreamAlias("PostalDirectoryMatchLevel")
    private String postalDirectoryMatchLevel;

    @XStreamAlias("DeliveryConfirmationCode")
    private String deliveryConfirmationCode;

    @XStreamAlias("Country")
    private String country;

    @XStreamAlias("MetropolitanStatisticalArea")
    private String metropolitanStatisticalArea;

    public CivicAddressValidationResult() {
    }

    public String getCensusMatchLevel() {
        return censusMatchLevel;
    }

    public void setCensusMatchLevel(String censusMatchLevel) {
        this.censusMatchLevel = censusMatchLevel;
    }

    public ArrayOfCivicAddress getCivicAddresses() {
        return civicAddresses;
    }

    public void setCivicAddresses(ArrayOfCivicAddress civicAddresses) {
        this.civicAddresses = civicAddresses;
    }

public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getDeliveryConfirmationCode() {
        return deliveryConfirmationCode;
    }

    public void setDeliveryConfirmationCode(String deliveryConfirmationCode) {
        this.deliveryConfirmationCode = deliveryConfirmationCode;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getMetropolitanStatisticalArea() {
        return metropolitanStatisticalArea;
    }

    public void setMetropolitanStatisticalArea(String metropolitanStatisticalArea) {
        this.metropolitanStatisticalArea = metropolitanStatisticalArea;
    }

    public String getPostalDirectoryMatchLevel() {
        return postalDirectoryMatchLevel;
    }

    public void setPostalDirectoryMatchLevel(String postalDirectoryMatchLevel) {
        this.postalDirectoryMatchLevel = postalDirectoryMatchLevel;
    }

    public ResultColor getResult() {
        return result;
    }

    public void setResult(ResultColor result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CivicAddressValidationResult{" +
            "censusMatchLevel='" + censusMatchLevel + '\'' +
            ", result=" + result +
            ", civicAddresses=" + civicAddresses +
            ", geoPoint=" + geoPoint +
            ", postalDirectoryMatchLevel='" + postalDirectoryMatchLevel + '\'' +
            ", deliveryConfirmationCode='" + deliveryConfirmationCode + '\'' +
            ", country='" + country + '\'' +
            ", metropolitanStatisticalArea='" + metropolitanStatisticalArea + '\'' +
            '}';
    }
}
