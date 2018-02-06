package com.centurylink.pctl.mod.cart.domain.jms;

import com.centurylink.pctl.mod.cart.controllers.rest.PctlCartRestController;
import com.centurylink.pctl.mod.cart.domain.ShoppingCart.InvalidCartEventTypeException;
import com.centurylink.pctl.mod.cart.domain.ShoppingCart.PctlCartService;
import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener {
    @Autowired
    public PctlCartService shoppingCartService;

    @Autowired
    public PctlCartRestController pctlCartRestController;

    private static final Logger log = LoggerFactory.getLogger(MyMessageListener.class);

    public ShoppingCartEvent<ShoppingCart> onMessage(ShoppingCartEvent<CartEvent> shoppingCartEvent) {
        log.info("Inside the method MyMessageListener.onMessage");
        ShoppingCartEvent shoppingCartEventShoppingCart = null;
        try {
            shoppingCartService.addCartEvent(shoppingCartEvent.getEventAttributes());
            shoppingCartEventShoppingCart = shoppingCartEvent;
        } catch ( InvalidCartEventTypeException e) {
            e.printStackTrace();
        }

        if(shoppingCartEvent.getEventAttributes().getCartEventType().name().toString().equalsIgnoreCase("CHECKOUT")) {

             shoppingCartEventShoppingCart = new ShoppingCartEvent(shoppingCartEvent.getEventAttributes().getCartId(),
                    shoppingCartEvent.getHeader(), shoppingCartEvent.getUserInfo(),
                    pctlCartRestController.getCheckOutCart(shoppingCartEvent.getEventAttributes().getCartId()));
              shoppingCartEventShoppingCart.getHeader().setName("ORDER_INIT");
            return shoppingCartEventShoppingCart;
        }
        return shoppingCartEventShoppingCart;
    }


    public ShoppingCartEvent<ShoppingCart>  onHandle(ShoppingCartEvent<ShoppingCart> m) {
         return m;
    }
}
