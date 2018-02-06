package com.centurylink.pctl.mod.order.domain.jms;

import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.order.domain.order.PctlOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener {
    private static final Logger log = LoggerFactory.getLogger(MyMessageListener.class);


    @Autowired
    public PctlOrderService pctlOrderService;

    public ShoppingCartEvent onMessage(ShoppingCartEvent shoppingCartEvent) throws Exception {
        log.info("Inside the method MyMessageListener.onMessage");
        if (shoppingCartEvent.getHeader().getName().equalsIgnoreCase("ORDER_INIT")) {
            log.info("Inside the method MyMessageListener.getOrderInit");
            pctlOrderService.cartCheckout((ShoppingCart) shoppingCartEvent.getEventAttributes(),
                shoppingCartEvent.getUserInfo().getOrderInfo().getOrderId());
            return shoppingCartEvent;
        }

        if (shoppingCartEvent.getHeader().getName().equalsIgnoreCase("ORDER_EVENTS")) {
            log.info("Inside the method MyMessageListener.ORDER_EVENTS");
            if (((OrderEvent) shoppingCartEvent.getEventAttributes()).getOrderEventType().name().toString().equalsIgnoreCase("ORDERED")) {
                shoppingCartEvent.getHeader().setName("SFAINT");
                pctlOrderService.orderSummary(((OrderEvent) shoppingCartEvent.getEventAttributes()).getOrderId());
                shoppingCartEvent.setEventAttributes(pctlOrderService.orderSummary(((OrderEvent) shoppingCartEvent.getEventAttributes()).getOrderId()));
                return shoppingCartEvent;
            }
            pctlOrderService.orderEvent((OrderEvent) shoppingCartEvent.getEventAttributes());
            if (((OrderEvent) shoppingCartEvent.getEventAttributes()).getOrderEventType().name().toString().equalsIgnoreCase("UPDATED")) {
                shoppingCartEvent.getHeader().setName("ORDER_UPDATE");
            }

            return shoppingCartEvent;
        }
        return shoppingCartEvent;

    }


    public ShoppingCartEvent onHandle(ShoppingCartEvent shoppingCartEvent) {

        return shoppingCartEvent;
    }
}
