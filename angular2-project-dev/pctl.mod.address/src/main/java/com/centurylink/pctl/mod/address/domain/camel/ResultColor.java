
package com.centurylink.pctl.mod.address.domain.camel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


public enum ResultColor {

    @XmlEnumValue("Green")
    GREEN("Green"),
    @XmlEnumValue("Red")
    RED("Red"),
    @XmlEnumValue("Yellow")
    YELLOW("Yellow");
    private final String value;

    ResultColor(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResultColor fromValue(String v) {
        for (ResultColor c: ResultColor.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
