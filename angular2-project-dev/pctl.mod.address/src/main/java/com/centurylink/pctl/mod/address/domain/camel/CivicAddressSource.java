
package com.centurylink.pctl.mod.address.domain.camel;

public enum CivicAddressSource {

    TRILLIUM,
    GOOGLE;

    public String value() {
        return name();
    }

    public static CivicAddressSource fromValue(String v) {
        return valueOf(v);
    }

}
