package com.centurylink.pctl.mod.sfaint.modal;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 18-11-2016.
 */
@Component
public class CreditCheckRequest implements Serializable {

    private String oppotunityID;

    private boolean pullback;

    public CreditCheckRequest() {
    }

    public String getOppotunityID() {
        return oppotunityID;
    }

    public void setOppotunityID(String oppotunityID) {
        this.oppotunityID = oppotunityID;
    }

    public boolean isPullback() {
        return pullback;
    }

    public void setPullback(boolean pullback) {
        this.pullback = pullback;
    }

    @Override
    public String toString() {
        return "CreditCheckRequest{" +
            "oppotunityID='" + oppotunityID + '\'' +
            ", pullback=" + pullback +
            '}';
    }


}
