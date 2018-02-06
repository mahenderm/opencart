package com.centurylink.pctl.mod.notification.controllers.rest;


import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.notification.domain.EmailStatus;
import com.centurylink.pctl.mod.notification.domain.Notification;
import com.centurylink.pctl.mod.notification.domain.PctlNotificationResponse;
import com.centurylink.pctl.mod.notification.domain.PctlNotificationService;
import com.centurylink.pctl.mod.notification.domain.utils.DeliveryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * PctlNotificationRestController is a controller class configured using SpringMVC concept
 * uses @RequestMapping as "/notification/{type}"
 * <br> PctlNotificationRestController class Contains one method
 *  <br>    - notificationType()
 *
 * @author srinivas
 */

@Transactional
@RestController
@RequestMapping("/notification")
public class PctlNotificationRestController {


    private static final Logger log = LoggerFactory.getLogger(PctlNotificationRestController.class);

        /**
     * PctlNotificationService is autowired to make service call to PctlNotificationRepository
     */
    @Autowired
    private PctlNotificationService pctlNotificationService;
    /**
     * notificationType() identifies whether email or message or any other delivery type
     * If deliveryType is null, notificationType() method will set status as N401
     * notificationType() method configured for @RequestMapping as  "/{type}"
     * @param type, Either email or message type has to be given as input parameter in url.
     * @return NotificationResponse is response object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{type}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response<PctlNotificationResponse>> notificationType(@PathVariable String type) {
        Response<PctlNotificationResponse> responseList = new Response<PctlNotificationResponse>();
        DeliveryType deliveryType = DeliveryType.getValueOf(type);
        if (deliveryType == null) {
            responseList.setStatus(StatusCode.N401);
            return new ResponseEntity<Response<PctlNotificationResponse>>(responseList, HttpStatus.BAD_REQUEST);
        }
        List<Notification> response = pctlNotificationService.findNotificationByDeliveryType(deliveryType.name());
        PctlNotificationResponse notResponse = new PctlNotificationResponse();
        notResponse.setResponse(response);
        responseList.setContent(notResponse);
        responseList.setStatus(StatusCode.U200);
        return new ResponseEntity<Response<PctlNotificationResponse>>(responseList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Response> sendAllEmailNotification() {
        List<Response> responseList = new ArrayList<Response>();
        List<Notification> notifications = pctlNotificationService.findNotificationByDeliveryTypeAndDeliveryStatus("EMAIL", "0");
        if (notifications != null && notifications.size() > 0) {
            for (Notification notification : notifications) {
                Response response = null;
                if (pctlNotificationService.sendEmail(notification).equals(EmailStatus.SUCCESS)) {
                    response = new Response(StatusCode.U200.getCode(), "Notification " + notification.get_id() + " has been sent ", StatusCode.U200.getHttpStatus());
                    pctlNotificationService.updateNotification(notification.get_id());
                } else {
                    response = new Response(StatusCode.U400.getCode(), "Notification " + notification.get_id() + " has not been sent ", StatusCode.U400.getHttpStatus());
                }
                responseList.add(response);
            }
        } else {

            Response response = new Response(StatusCode.U200.getCode(), "There is no notification", StatusCode.U200.getHttpStatus());
            responseList.add(response);
        }

        return responseList;
    }
}



