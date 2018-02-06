package com.centurylink.pctl.mod.sfaint.modal;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 22-11-2016.
 */
@Component
public class CreateLeadResponse implements Serializable{
    private  boolean success;
    private String message;
    private String leadId;

    public CreateLeadResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LeadResponse{" +
            "success='" + success + '\'' +
            ", message='" + message + '\'' +
            ", leadId='" + leadId + '\'' +
            '}';
    }
}
