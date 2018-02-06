package com.centurylink.pctl.mod.sfaint.modal.jms;

import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.sfaint.domain.service.PctlSfaintService;
import com.centurylink.pctl.mod.sfaint.modal.SfaintEvent;
import com.centurylink.pctl.mod.sfaint.modal.SfaintEventType;
import com.centurylink.pctl.mod.sfaint.modal.SfaintStatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PctlSfaintHandler {

    @Autowired
    private PctlSfaintService sfaintService;
    private static final Logger log = LoggerFactory.getLogger(PctlSfaintHandler.class);


    public SfaintEvent onMessage(ShoppingCartEvent shoppingCartEvent) {


        log.info(" Handler of SFAINT Receiver Queue");
        SfaintEvent sfaintEvent = new SfaintEvent();

        if (shoppingCartEvent.getEventAttributes() != null && shoppingCartEvent.getEventAttributes() instanceof OrderEvent) {
            sfaintEvent.setOrderEvent((OrderEvent) shoppingCartEvent.getEventAttributes());

            if (shoppingCartEvent.getUserInfo().getStatus() != null && shoppingCartEvent.getUserInfo().getStatus().equalsIgnoreCase("Y")) {
                sfaintEvent.setSfaintEventType(SfaintEventType.CREATE_OPPURTUNITY);
                sfaintEvent.setStatus(SfaintStatusType.SFAINT_CREATE_OPPURTUNITY_INIT);
                sfaintService.sfaintevent(sfaintEvent);
            }
            else if (shoppingCartEvent.getUserInfo().getStatus() != null &&
                shoppingCartEvent.getUserInfo().getStatus().equalsIgnoreCase("N"))
            {
                sfaintEvent.setSfaintEventType(SfaintEventType.CREATE_ACCOUNT);
                sfaintEvent.setStatus(SfaintStatusType.SFAINT_ACCOUNT_CREATION_INIT);
                sfaintService.sfaintevent(sfaintEvent);
            }

        }

        return sfaintEvent;
    }

    public SfaintEvent onHandle(SfaintEvent sfaintEvent) {
        return sfaintEvent;
    }
}
