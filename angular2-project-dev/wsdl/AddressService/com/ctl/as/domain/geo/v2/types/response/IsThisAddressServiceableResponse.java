
package com.ctl.as.domain.geo.v2.types.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.ctl.as.domain.geo.types.ArrayOfError;
import com.ctl.as.domain.geo.types.ResultColor;
import com.ctl.as.domain.geo.v2.types.ArrayOfServiceAddress;


/**
 * <p>Java class for IsThisAddressServiceableResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="IsThisAddressServiceableResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://geo.domain.as.ctl.com/v2/Types/Response}AddressManagementResponse">
 *       &lt;sequence>
 *         &lt;element name="Errors" type="{http://geo.domain.as.ctl.com/Types}ArrayOfError" minOccurs="0"/>
 *         &lt;element name="Result" type="{http://geo.domain.as.ctl.com/Types}ResultColor" minOccurs="0"/>
 *         &lt;element name="IsResponseFromCache" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ServiceAddresses" type="{http://geo.domain.as.ctl.com/v2/Types}ArrayOfServiceAddress" minOccurs="0"/>
 *         &lt;element name="RelatedAddresses" type="{http://geo.domain.as.ctl.com/v2/Types}ArrayOfServiceAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IsThisAddressServiceableResponse", propOrder = {
    "errors",
    "result",
    "isResponseFromCache",
    "serviceAddresses",
    "relatedAddresses"
})
public class IsThisAddressServiceableResponse
    extends AddressManagementResponse
{

    @XmlElementRef(name = "Errors", namespace = "http://geo.domain.as.ctl.com/v2/Types/Response", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfError> errors;
    @XmlElementRef(name = "Result", namespace = "http://geo.domain.as.ctl.com/v2/Types/Response", type = JAXBElement.class, required = false)
    protected JAXBElement<ResultColor> result;
    @XmlElement(name = "IsResponseFromCache")
    protected Boolean isResponseFromCache;
    @XmlElementRef(name = "ServiceAddresses", namespace = "http://geo.domain.as.ctl.com/v2/Types/Response", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfServiceAddress> serviceAddresses;
    @XmlElementRef(name = "RelatedAddresses", namespace = "http://geo.domain.as.ctl.com/v2/Types/Response", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfServiceAddress> relatedAddresses;

    /**
     * Gets the value of the errors property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfError }{@code >}
     *
     */
    public JAXBElement<ArrayOfError> getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfError }{@code >}
     *
     */
    public void setErrors(JAXBElement<ArrayOfError> value) {
        this.errors = value;
    }

    /**
     * Gets the value of the result property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ResultColor }{@code >}
     *
     */
    public JAXBElement<ResultColor> getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ResultColor }{@code >}
     *
     */
    public void setResult(JAXBElement<ResultColor> value) {
        this.result = value;
    }

    /**
     * Gets the value of the isResponseFromCache property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIsResponseFromCache() {
        return isResponseFromCache;
    }

    /**
     * Sets the value of the isResponseFromCache property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsResponseFromCache(Boolean value) {
        this.isResponseFromCache = value;
    }

    /**
     * Gets the value of the serviceAddresses property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfServiceAddress }{@code >}
     *
     */
    public JAXBElement<ArrayOfServiceAddress> getServiceAddresses() {
        return serviceAddresses;
    }

    /**
     * Sets the value of the serviceAddresses property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfServiceAddress }{@code >}
     *
     */
    public void setServiceAddresses(JAXBElement<ArrayOfServiceAddress> value) {
        this.serviceAddresses = value;
    }

    /**
     * Gets the value of the relatedAddresses property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfServiceAddress }{@code >}
     *
     */
    public JAXBElement<ArrayOfServiceAddress> getRelatedAddresses() {
        return relatedAddresses;
    }

    /**
     * Sets the value of the relatedAddresses property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link ArrayOfServiceAddress }{@code >}
     *
     */
    public void setRelatedAddresses(JAXBElement<ArrayOfServiceAddress> value) {
        this.relatedAddresses = value;
    }

}
