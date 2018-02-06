package com.centurylink.pctl.mod.sfaint.modal;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 21-11-2016.
 */
@Component
public class CreateOppurtunityResponse implements Serializable{

    private String opportunityId;
    private String errorMessage;
    private String errorCode;
    private String status;

    public CreateOppurtunityResponse() {
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SfaintResponseCheck{" +
            "opportunityId='" + opportunityId + '\'' +
            ", errorMessage='" + errorMessage + '\'' +
            ", errorCode='" + errorCode + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
