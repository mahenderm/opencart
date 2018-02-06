
package com.ctl.as.domain.geo.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputAddress", propOrder = {
    "streetAddress",
    "parsedStreetAddress",
    "subAddress",
    "parsedSubAddress",
    "locality",
    "stateOrProvince",
    "postCode"
})
public class InputAddress {

    @XmlElementRef(name = "StreetAddress", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<String> streetAddress;
    @XmlElementRef(name = "ParsedStreetAddress", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<ParsedStreetAddress> parsedStreetAddress;
    @XmlElementRef(name = "SubAddress", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subAddress;
    @XmlElementRef(name = "ParsedSubAddress", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSubAddressDesignator> parsedSubAddress;
    @XmlElementRef(name = "Locality", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<String> locality;
    @XmlElementRef(name = "StateOrProvince", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateOrProvince;
    @XmlElementRef(name = "PostCode", namespace = "http://geo.domain.as.ctl.com/Types", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postCode;

    /**
     * Gets the value of the streetAddress property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the value of the streetAddress property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setStreetAddress(JAXBElement<String> value) {
        this.streetAddress = value;
    }

    /**
     * Gets the value of the parsedStreetAddress property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ParsedStreetAddress }{@code >}
     *
     */
    public JAXBElement<ParsedStreetAddress> getParsedStreetAddress() {
        return parsedStreetAddress;
    }

    /**
     * Sets the value of the parsedStreetAddress property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ParsedStreetAddress }{@code >}
     *
     */
    public void setParsedStreetAddress(JAXBElement<ParsedStreetAddress> value) {
        this.parsedStreetAddress = value;
    }

    /**
     * Gets the value of the subAddress property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSubAddress() {
        return subAddress;
    }

    /**
     * Sets the value of the subAddress property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSubAddress(JAXBElement<String> value) {
        this.subAddress = value;
    }

    /**
     * Gets the value of the parsedSubAddress property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfSubAddressDesignator }{@code >}
     *
     */
    public JAXBElement<ArrayOfSubAddressDesignator> getParsedSubAddress() {
        return parsedSubAddress;
    }

    /**
     * Sets the value of the parsedSubAddress property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfSubAddressDesignator }{@code >}
     *
     */
    public void setParsedSubAddress(JAXBElement<ArrayOfSubAddressDesignator> value) {
        this.parsedSubAddress = value;
    }

    /**
     * Gets the value of the locality property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
          *
     */
    public JAXBElement<String> getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
          *
          */
    public void setLocality(JAXBElement<String> value) {
        this.locality = value;
    }

    /**
     * Gets the value of the stateOrProvince property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
          */
         public JAXBElement<String> getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Sets the value of the stateOrProvince property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
         public void setStateOrProvince(JAXBElement<String> value) {
        this.stateOrProvince = value;
    }

    /**
     * Gets the value of the postCode property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPostCode(JAXBElement<String> value) {
        this.postCode = value;
    }

}
