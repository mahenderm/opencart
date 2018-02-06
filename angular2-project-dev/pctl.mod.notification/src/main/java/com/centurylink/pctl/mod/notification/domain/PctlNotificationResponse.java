package com.centurylink.pctl.mod.notification.domain;



import com.centurylink.pctl.mod.core.utils.Response;

import java.util.List;

/**
 * @author pulapakas
 */
public class PctlNotificationResponse {
    /**
     * messageNotification in PctlNotificationResponse
     */
    public Response<Notification> messageNotification;
    /**
     * emailNotification in PctlNotificationResponse
     */
    public Response<Notification> emailNotification;
    /**
     * response in PctlNotificationResponse
     */
    List<Notification> response;

    /**
     *  Notification list response in PctlNotificationResponse
     * @return messageNotification
     */
    public Response<Notification> getMessageNotification() {
        return messageNotification;
    }

    /**
     * emailNotification in PctlNotificationResponse
     * @return emailNotification
     */
    public Response<Notification> getEmailNotification() {
        return emailNotification;
    }

    /**
     * list of notification in PctlNotificationResponse
     * @return
     */
    public List<Notification> getResponse() {
        return response;
    }

    /**
     * set method for PctlNotificationResponse
     * @param response
     */
    public void setResponse(List<Notification> response) {
        this.response = response;
    }


}
