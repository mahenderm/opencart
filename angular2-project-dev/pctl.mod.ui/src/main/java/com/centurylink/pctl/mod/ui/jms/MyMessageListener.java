package com.centurylink.pctl.mod.ui.jms;

import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener {


  public ShoppingCartEvent  onMessage(ShoppingCartEvent shoppingCartEvent) {
    return shoppingCartEvent;
  }


  public ShoppingCartEvent  onHandle(ShoppingCartEvent shoppingCartEvent) {
    try {
      System.out.println(shoppingCartEvent);
          } catch (Exception e) {
    }
    return shoppingCartEvent;
  }
}
