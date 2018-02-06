package com.centurylink.pctl.mod.sfaint.modal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 17-11-2016.
 */
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("UUID")
    private String UUID;

    private boolean success;
    private String message;
    private String accountId;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonCreator
    public CreateAccountResponse() {
    }

    @JsonCreator
    public CreateAccountResponse( @JsonProperty("UUID") String UUID, boolean success, String message, String accountId) {
        this.UUID = UUID;
        this.success = success;
        this.message = message;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "CreateAccountResponse{" +
            "UUID='" + UUID + '\'' +
            ", success=" + success +
            ", message='" + message + '\'' +
            ", accountId='" + accountId + '\'' +
            '}';
    }
}
