
package com.centurylink.pctl.mod.address.domain.camel;


public enum GeoPointSource {

    CLC,
    GOOGLE,
    GPS,
    MARTENS,
    TRILLIUM;

    public String value() {
        return name();
    }

    public static GeoPointSource fromValue(String v) {
        return valueOf(v);
    }

}
