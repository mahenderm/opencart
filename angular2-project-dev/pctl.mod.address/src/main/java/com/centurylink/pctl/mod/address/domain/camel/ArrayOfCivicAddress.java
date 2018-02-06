
package com.centurylink.pctl.mod.address.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;


public class ArrayOfCivicAddress {

   // @XStreamAlias("CivicAddress")
    @XStreamImplicit(itemFieldName ="CivicAddress")
    protected List<CivicAddress> civicAddress;

    public ArrayOfCivicAddress() {

    }

    public List<CivicAddress> getCivicAddress() {
        if (civicAddress == null) {
            civicAddress = new ArrayList<CivicAddress>();
        }
        return this.civicAddress;
    }

    public void setCivicAddress(List<CivicAddress> civicAddress) {
        this.civicAddress = civicAddress;
    }

    @Override
    public String toString() {
        return "ArrayOfCivicAddress{" +
            "civicAddress=" + civicAddress +
            '}';
    }

}
