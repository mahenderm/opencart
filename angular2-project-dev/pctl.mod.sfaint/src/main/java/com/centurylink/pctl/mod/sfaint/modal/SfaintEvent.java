package com.centurylink.pctl.mod.sfaint.modal;

import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 24-11-2016.
 */
@Component
public class SfaintEvent implements Serializable{
    private CreateLeadRequest createLeadRequest;
    private CreateOppurtunityRequest createOppurtunityRequest;
    private CreditCheckRequest creditCheckRequest;
    private OrderEvent orderEvent;
    private SfaintStatusType status;

    public SfaintEvent() {
    }

    public OrderEvent getOrderEvent() {
        return orderEvent;
    }

    public void setOrderEvent(OrderEvent orderEvent) {
        this.orderEvent = orderEvent;
    }

    public CreditCheckRequest getCreditCheckRequest() {
        return creditCheckRequest;
    }

    public void setCreditCheckRequest(CreditCheckRequest creditCheckRequest) {
        this.creditCheckRequest = creditCheckRequest;
    }

    private CreateAccountRequest createAccountRequest;



    private SfaintEventType sfaintEventType;

    public CreateLeadRequest getCreateLeadRequest() {
        return createLeadRequest;
    }

    public void setCreateLeadRequest(CreateLeadRequest createLeadRequest) {
        this.createLeadRequest = createLeadRequest;
    }


    public SfaintEventType getSfaintEventType() {
        return sfaintEventType;
    }

    public void setSfaintEventType(SfaintEventType sfaintEventType) {
        this.sfaintEventType = sfaintEventType;
    }

    public CreateOppurtunityRequest getCreateOppurtunityRequest() {
        return createOppurtunityRequest;
    }

    public void setCreateOppurtunityRequest(CreateOppurtunityRequest createOppurtunityRequest) {
        this.createOppurtunityRequest = createOppurtunityRequest;
    }

    public CreateAccountRequest getCreateAccountRequest() {
        return createAccountRequest;
    }

    public void setCreateAccountRequest(CreateAccountRequest createAccountRequest) {
        this.createAccountRequest = createAccountRequest;
    }

    public SfaintStatusType getStatus() {
        return status;
    }

    public void setStatus(SfaintStatusType status) {
        this.status = status;
    }
}
