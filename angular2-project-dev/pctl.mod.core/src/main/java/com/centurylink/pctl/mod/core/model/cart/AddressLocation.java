package com.centurylink.pctl.mod.core.model.cart;

import com.centurylink.pctl.mod.core.model.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * AddressLocation Bean
 *
 * @author nagavenkatakirang
 */
public class AddressLocation implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * id of AddressLocation
     */
    private String id;
    /**
     * serviceAddress of AddressLocation
     */
    private Address serviceAddress;
    /**
     * shippingAddress of AddressLocation
     */
    private Address shippingAddress;
    /**
     * serviceContact of AddressLocation
     */
    private ServiceContact serviceContact;
    /**
     * locationCount of AddressLocation
     */
    @JsonIgnore
    private int locationCount;
    //constructors
    public AddressLocation(){}

    public AddressLocation(String id, Address serviceAddress, Address shippingAddress) {
        this.id = id;
        this.serviceAddress = serviceAddress;
        this.shippingAddress = shippingAddress;
        this.locationCount = 1;
    }

    public AddressLocation(String id, Address serviceAddress, Address shippingAddress, ServiceContact contactInfo) {
        this.id = id;
        this.serviceAddress = serviceAddress;
        this.shippingAddress = shippingAddress;
        this.serviceContact = contactInfo;
        this.locationCount = 1;
    }

    public AddressLocation(String id, Address serviceAddress, Address shippingAddress, ServiceContact contactInfo, int locationCount) {
        this.id = id;
        this.serviceAddress = serviceAddress;
        this.shippingAddress = shippingAddress;
        this.serviceContact = contactInfo;
        this.locationCount = locationCount;
    }
    //getters and setters
    /**
     * getter for serviceContact
     * @return serviceContact
     */
    public ServiceContact getServiceContact() {
        return serviceContact;
    }
    /**
     * setter for serviceContact
     * @param serviceContact serviceContact of AddressLocation
     */
    public void setServiceContact(ServiceContact serviceContact) {
        this.serviceContact = serviceContact;
    }
    /**
     * getter for id
     * @return id
     */
    public String getId() {
        return id;
    }
    /**
     * setter for id
     * @param id id of AddressLocation
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * getter for serviceAddress
     * @return serviceAddress
     */
    public Address getServiceAddress() {
        return serviceAddress;
    }
    /**
     * setter for serviceAddress
     * @param serviceAddress service Address of AddressLocation
     */
    public void setServiceAddress(Address serviceAddress) {
        this.serviceAddress = serviceAddress;
    }
    /**
     * getter for shippingAddress
     * @return shippingAddress
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }
    /**
     * setter for shippingAddress
     * @param shippingAddress shipping Address of AddressLocation
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    /**
     * getter for locationCount
     * @return locationCount
     */
    public int getLocationCount() {
        return locationCount;
    }
    /**
     * setter for locationCount
     * @param locationCount number of locations of Address
     */
    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }
    /**
     * toString() method is overridden to return the state of the AddressLocation
     */
    @Override
    public String toString() {
        return "AddressLocation{" +
            "id='" + id + '\'' +
            ", serviceAddress=" + serviceAddress +
            ", shippingAddress=" + shippingAddress +
            ", serviceContact=" + serviceContact +
            ", locationCount=" + locationCount +
            '}';
    }
}
