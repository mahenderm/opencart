package com.centurylink.pctl.mod.notification.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.List;

/**
 * This class PctlNotificationService is a service class for PctlNotificationRestController.
 * <br>This class PctlNotificationService makes a call to the PctlNotificationRepository class methods.
 * @author pulapakas
 */
@Service
public class PctlNotificationService {
    private final Logger log = LoggerFactory.getLogger(PctlNotificationService.class);

    @Autowired
    public EmailService emailService;

    @Autowired
    private PctlNotificationRepository notificationRepository;

    /**
     * This findNotificationByDeliveryType() method accepts deliveryType as input and returns respective Notification Lists from database.
     * This findNotificationByDeliveryType() method calls notificationRepository classes findNotificationByDeliveryType () method
     * @param deliveryType, available deliveryType from database
     * @return Notification list , returns respective List of Notification for the given deliveryType available in database
     */
    public List<Notification> findNotificationByDeliveryType(String deliveryType) {
        log.info("findNotificationByDeliveryType" + deliveryType);
        return notificationRepository.findNotificationByDeliveryType(deliveryType);
    }


    /**
     * This findNotificationByDeliveryTypeAndDeliveryStatus() method accepts deliveryType, deliveryStatus as input and returns respective Notification Lists from database.
     * This findNotificationByDeliveryTypeAndDeliveryStatus() method calls notificationRepository classes findNotificationByDeliveryTypeAndDeliveryStatus () method
     * @param deliveryType, available deliveryType from database
     * @param deliveryStatus, available deliveryType from database
     * @return Notification list , returns respective List of Notification for the given deliveryType, deliveryStatus available in database
     */
    public List<Notification> findNotificationByDeliveryTypeAndDeliveryStatus(String deliveryType,String deliveryStatus) {
        log.info("findNotificationByDeliveryTypeAndDeliveryStatus" + deliveryType);
        log.info("findNotificationByDeliveryTypeAndDeliveryStatus" + deliveryStatus);

        return notificationRepository.findNotificationByDeliveryTypeAndDeliveryStatus(deliveryType,deliveryStatus);
    }




    public EmailStatus sendEmail(Notification notification) {

        Context context = new Context();
        context.setVariable("title", notification.getSubject());
        context.setVariable("description", notification.getMessage());
        EmailStatus emailStatus = emailService.sendHTMLEmail(notification.getToAddress().get(0), notification.getSubject(), "email/template-1", context);
        return  emailStatus;
    }

    public void updateNotification(String id ){

        Notification   notification = notificationRepository.findNotificationById(id);
        if(notification != null){
              notification.setDeliveryStatus("1");
              notificationRepository.save(notification);
        }
    }
}
