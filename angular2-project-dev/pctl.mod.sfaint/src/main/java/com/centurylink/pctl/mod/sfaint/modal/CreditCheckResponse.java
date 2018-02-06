package com.centurylink.pctl.mod.sfaint.modal;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 21-11-2016.
 */
@Component
public class CreditCheckResponse implements Serializable{

    private String errorCode;
    private String errorMessage;
    private String Status;
    private String creditStatus;

    public CreditCheckResponse() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    @Override
    public String toString() {
        return "CreditCheckResponse{" +
            "errorCode='" + errorCode + '\'' +
            ", errorMessage='" + errorMessage + '\'' +
            ", Status='" + Status + '\'' +
            ", creditStatus='" + creditStatus + '\'' +
            '}';
    }
}
