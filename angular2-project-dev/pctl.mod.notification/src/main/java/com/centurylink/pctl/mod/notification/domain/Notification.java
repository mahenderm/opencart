package com.centurylink.pctl.mod.notification.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Notification bean class
 * @author pulapakas
 */
@Document(collection = "notification")
public class Notification implements Serializable {

    /**
     * id of Notification
     */
    @Id
    private String _id;
    /**
     * subject of Notification
     */
    private String subject;
    /**
     * message of Notification
     */
    private String message;
    /**
     * email of Notification
     */
    private String email;
    /**
     * fromAddress of Notification
     */
    private String fromAddress;
    /**
     * toAddress of Notification
     */
    private List<String> toAddress;
    /**
     * notificationType of Notification
     */
    private EventType notificationType;
    /**
     * deliveryType of Notification
     */
    private String deliveryType;
    /**
     * CreatedDate of Notification
     */
    private String CreatedDate;
    /**
     * deliveryDate of Notification
     */
    private String deliveryDate;

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * deliveryStatus of Notification
     */
    private String deliveryStatus;
    /**
     * entityId of Notification
     */
    private String entityId;
    /**
     * emailId of Notification
     */
    private String emailId;
    /**
     * entityType of Notification
     */
    private String entityType;
    /**
     * constructor of Notification
     */

    public Notification() {
    }

    /**
     *  get method for email in Notification
     * @return email
     */
    public String getEmail() {
        return email;
    }



    /**
     *  entityId in Notification
     * @return entityId
     */
    public String getEntityId() {
        return entityId;
    }


    /**
     *  entityType in Notification
     * @return entityType
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     *  id in Notification
     * @return id
     */
    public String get_id() {
        return _id;
    }

    /**
     *  deliveryStatus in Notification
     * @return deliveryStatus
     */
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     *  subject in Notification
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *  message in Notification
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     *  fromAddress in Notification
     * @return fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     *  toAddress in Notification
     * @return toAddress
     */
    public List<String> getToAddress() {
        return toAddress;
    }

    /**
     *  notificationType in Notification
     * @return notificationType
     */
    public EventType getNotificationType() {
        return notificationType;
    }

    /**
     *  deliveryType in Notification
     * @return deliveryType
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     *  CreatedDate in Notification
     * @return CreatedDate
     */
    public String getCreatedDate() {
        return CreatedDate;
    }

    /**
     *  deliveryDate in Notification
     * @return deliveryDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

}
