
package com.centurylink.pctl.mod.address.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CivicAddress {



    @XStreamAlias("StreetAddress")
    protected String streetAddress;
    @XStreamAlias("StreetNrFirst")
    protected String streetNrFirst;
    @XStreamAlias("StreetName")
    protected String streetName;
    @XStreamAlias("StreetType")
    protected String streetType;
    @XStreamAlias("Locality")
    protected String locality;
    @XStreamAlias("StateOrProvince")
    protected String stateOrProvince;
    @XStreamAlias("PostCode")
    protected String postCode;

    @XStreamAlias("Source")
    protected CivicAddressSource source;
    @XStreamAlias("StreetComponentsChanged")
    protected Boolean streetComponentsChanged;
    @XStreamAlias("StreetNameChanged")
    protected Boolean streetNameChanged;
    @XStreamAlias("LocalityChanged")
    protected Boolean localityChanged;
    @XStreamAlias("PostCodeChanged")
    protected Boolean postCodeChanged;


    @XStreamAlias("parsedStreetAddress")
    protected ParsedStreetAddress parsedStreetAddress;
    @XStreamAlias("SubAddress")
    protected String subAddress;

    public CivicAddress() {
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Boolean getLocalityChanged() {
        return localityChanged;
    }

    public void setLocalityChanged(Boolean localityChanged) {
        this.localityChanged = localityChanged;
    }

    public ParsedStreetAddress getParsedStreetAddress() {
        return parsedStreetAddress;
    }

    public void setParsedStreetAddress(ParsedStreetAddress parsedStreetAddress) {
        this.parsedStreetAddress = parsedStreetAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Boolean getPostCodeChanged() {
        return postCodeChanged;
    }

    public void setPostCodeChanged(Boolean postCodeChanged) {
        this.postCodeChanged = postCodeChanged;
    }

    public CivicAddressSource getSource() {
        return source;
    }

    public void setSource(CivicAddressSource source) {
        this.source = source;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Boolean getStreetComponentsChanged() {
        return streetComponentsChanged;
    }

    public void setStreetComponentsChanged(Boolean streetComponentsChanged) {
        this.streetComponentsChanged = streetComponentsChanged;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Boolean getStreetNameChanged() {
        return streetNameChanged;
    }

    public void setStreetNameChanged(Boolean streetNameChanged) {
        this.streetNameChanged = streetNameChanged;
    }

    public String getStreetNrFirst() {
        return streetNrFirst;
    }

    public void setStreetNrFirst(String streetNrFirst) {
        this.streetNrFirst = streetNrFirst;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getSubAddress() {
        return subAddress;
    }

    public void setSubAddress(String subAddress) {
        this.subAddress = subAddress;
    }


    @Override
    public String toString() {

        return "CivicAddress{" +
            "locality='" + locality + '\'' +
            ", streetAddress='" + streetAddress + '\'' +
            ", streetNrFirst='" + streetNrFirst + '\'' +
            ", streetName='" + streetName + '\'' +
            ", streetType='" + streetType + '\'' +
            ", stateOrProvince='" + stateOrProvince + '\'' +
            ", postCode='" + postCode + '\'' +
            ", source=" + source +
            ", streetComponentsChanged=" + streetComponentsChanged +
            ", streetNameChanged=" + streetNameChanged +
            ", localityChanged=" + localityChanged +
            ", postCodeChanged=" + postCodeChanged +
            ", parsedStreetAddress=" + parsedStreetAddress +
            ", subAddress='" + subAddress + '\'' +
            '}';
    }
}
