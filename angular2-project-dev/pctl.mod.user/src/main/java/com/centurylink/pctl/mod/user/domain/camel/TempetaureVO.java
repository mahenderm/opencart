package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by senthilkumar-m on 02-11-2016.
 */
@XStreamAlias("CelsiusToFahrenheitResponse")
public class TempetaureVO {

    @XStreamAlias("CelsiusToFahrenheitResult")
    public String fHeatValue;


    @Override
    public String toString() {
        return "DataVO{" +
            "Cellious Value..+='" + fHeatValue + '}';
    }

}
