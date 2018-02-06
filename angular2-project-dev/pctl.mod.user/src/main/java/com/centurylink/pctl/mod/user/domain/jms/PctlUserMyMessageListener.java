package com.centurylink.pctl.mod.user.domain.jms;

import com.centurylink.pctl.mod.core.event.ShoppingCartEvent;
import com.centurylink.pctl.mod.user.controllers.rest.PctlUserServices;
import com.centurylink.pctl.mod.user.domain.user.PctlUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PctlUserMyMessageListener {
    private static final Logger log = LoggerFactory.getLogger(PctlUserMyMessageListener.class);
    @Autowired
    private PctlUserServices pctlUserServices;
    @Autowired
    private PctlUserRepository userRepository;

    public ShoppingCartEvent  onMessage(ShoppingCartEvent shoppingCartEvent) {
        log.info("Inside the method PctlUserMyMessageListener.onMessage");

           // LoggedUser loggedUser = userRepository.findOneByLogin(shoppingCartEvent.getUserInfo().getLogin());

            if(shoppingCartEvent.getHeader().getName().equalsIgnoreCase("CART-INIT")){
                pctlUserServices.postCart(shoppingCartEvent);
              }


        if(shoppingCartEvent.getHeader().getName().equalsIgnoreCase("ORDER_INIT")) {

            pctlUserServices.postOrder(shoppingCartEvent);

       /*     Set<OrderInfo> orderHist = new HashSet<OrderInfo>();
        //    if (shoppingCartEvent.getOrderInit()) {

                List<LineItem> lineItems = new ArrayList<>();
                ProductInfo productInfo = new ProductInfo();
                List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();

                lineItems = ((ShoppingCart)shoppingCartEvent.getEventAttributes())
                    .getLineItems();

               for (LineItem lineItemss: lineItems){
                   productInfo.setProduct(lineItemss.getProductName());
                   productInfo.setLocationSize(lineItemss.getLocations().size());
                   productInfo.setUnitPrice(lineItemss.getUnitPrice());
                   productInfoList.add(productInfo);

               }

                OrderInfo orderInfo = new OrderInfo(shoppingCartEvent.getUserInfo().getOrderInfo().getOrderId(),
                    shoppingCartEvent.getUserInfo().getCartInfo().getShoppingCartId(),
                    0,
                    shoppingCartEvent.getUserInfo().getOrderInfo().getOrderDate(),
                    productInfoList );

                if (loggedUser.getOrderHist().size() != 0) {
                    orderHist.addAll(loggedUser.getOrderHist());
                }
                if (loggedUser.getOrderInfo() != null) {
                    orderHist.add(loggedUser.getOrderInfo());
                }
                loggedUser.setOrderHist(orderHist);
                loggedUser.setOrderInfo(orderInfo);
           // }*/
        }

           if(shoppingCartEvent.getHeader().getName().equalsIgnoreCase("ORDER_UPDATE")){

       /*     if (((OrderEvent) shoppingCartEvent.getEventAttributes()).getOrderEventType()
                .toString().equalsIgnoreCase("UPDATED")) {*/

              /*  Set<Address> addressHist = new HashSet<Address>();
               if( !loggedUser.getBillingAddress().isEmpty()) {
                   if (loggedUser.getBillingAddress().size() != 0) {
                       addressHist.addAll(loggedUser.getBillingAddress());
                   }
               }
                addressHist.add(((OrderEvent) shoppingCartEvent.getEventAttributes()).getHqAddress());
                loggedUser.setBillingAddress(addressHist);*/
               pctlUserServices.orderUpdate(shoppingCartEvent);

           }
           //}
          //  userRepository.save(loggedUser);


        return shoppingCartEvent;
    }
    public String  onHandle(ShoppingCartEvent shoppingCartEvent) {
        return "";
    }
}
