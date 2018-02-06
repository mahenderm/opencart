
package com.centurylink.pctl.mod.address.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//@XStreamAlias("return")
public class CivicAddressValidationResponse extends AddressManagementResponse {


    @XStreamAlias("CivicAddressValidationResult")
    protected CivicAddressValidationResult civicAddressValidationResult;

    public CivicAddressValidationResult getCivicAddressValidationResult() {
        return civicAddressValidationResult;
    }

    public void setCivicAddressValidationResult(CivicAddressValidationResult civicAddressValidationResult) {
        this.civicAddressValidationResult = civicAddressValidationResult;
    }

    public CivicAddressValidationResponse() {
    }
}
