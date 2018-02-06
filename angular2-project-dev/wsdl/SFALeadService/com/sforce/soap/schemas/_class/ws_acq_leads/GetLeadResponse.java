
package com.sforce.soap.schemas._class.ws_acq_leads;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetLeadResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetLeadResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ACQLeads" type="{http://soap.sforce.com/schemas/class/WS_ACQ_Leads}ACQLead" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MessageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetLeadResponse", propOrder = {
    "acqLeads",
    "messageCode",
    "messageDesc",
    "totalNumber"
})
public class GetLeadResponse {

    @XmlElement(name = "ACQLeads", nillable = true)
    protected List<ACQLead> acqLeads;
    @XmlElementRef(name = "MessageCode", namespace = "http://soap.sforce.com/schemas/class/WS_ACQ_Leads", type = JAXBElement.class, required = false)
    protected JAXBElement<String> messageCode;
    @XmlElementRef(name = "MessageDesc", namespace = "http://soap.sforce.com/schemas/class/WS_ACQ_Leads", type = JAXBElement.class, required = false)
    protected JAXBElement<String> messageDesc;
    @XmlElementRef(name = "TotalNumber", namespace = "http://soap.sforce.com/schemas/class/WS_ACQ_Leads", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> totalNumber;

    /**
     * Gets the value of the acqLeads property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acqLeads property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACQLeads().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACQLead }
     *
     *
     */
    public List<ACQLead> getACQLeads() {
        if (acqLeads == null) {
            acqLeads = new ArrayList<ACQLead>();
        }
        return this.acqLeads;
    }

    /**
     * Gets the value of the messageCode property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMessageCode() {
        return messageCode;
    }

    /**
     * Sets the value of the messageCode property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMessageCode(JAXBElement<String> value) {
        this.messageCode = value;
    }

    /**
     * Gets the value of the messageDesc property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMessageDesc() {
        return messageDesc;
    }

    /**
     * Sets the value of the messageDesc property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMessageDesc(JAXBElement<String> value) {
        this.messageDesc = value;
    }

    /**
     * Gets the value of the totalNumber property.
     *
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public JAXBElement<Integer> getTotalNumber() {
        return totalNumber;
    }

    /**
     * Sets the value of the totalNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public void setTotalNumber(JAXBElement<Integer> value) {
        this.totalNumber = value;
    }

}
