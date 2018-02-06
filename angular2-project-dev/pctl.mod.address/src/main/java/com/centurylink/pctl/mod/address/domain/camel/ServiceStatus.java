
package com.centurylink.pctl.mod.address.domain.camel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


public enum ServiceStatus {


    COMPLETED("Completed"),

    FAILED("Failed");
    private final String value;

    ServiceStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceStatus fromValue(String v) {
        for (ServiceStatus c : ServiceStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
