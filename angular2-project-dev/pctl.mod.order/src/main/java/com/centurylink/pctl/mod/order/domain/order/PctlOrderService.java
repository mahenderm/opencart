package com.centurylink.pctl.mod.order.domain.order;

import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.order.OrderEventType;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * This class PctlOrderService is a service class for PctlOrderRestController.
 * <p>This class PctlOrderService makes a call to the PctlOrderEventRepository class methods,
 * and implements business logic for order service
 * </p>
 * <br> This class contains three methods
 * <br> - orderSummary()
 * <br> - orderEvent()
 * <br> - cartCheckout()
 *
 * @author veeraragavan.k
 */
@Service
public class PctlOrderService {

    @Autowired
    private PctlOrderEventRepository orderEventRep;
    @Autowired
    private PctlOrderRepository orderRep;

    /**
     * This orderSummary() accepts the order id and generates the order summary details
      * @param orderId, one of the available placed order id
     * @return Order, returns respective Order for the given orderId available in shopping cart object
     * @throws OrderException for orderSummary() if it is any error or failure in the operation
     */
    public Order orderSummary(String orderId) throws OrderException {

        Flux<OrderEvent> orderEventFlux = Flux.fromStream(orderEventRep.findOrderEventByOrderId(orderId));

        Order order = orderEventFlux.
            takeWhile(orderEvent -> orderEvent.getOrderEventType() != OrderEventType.DELIVERED)
            .reduceWith(() -> new Order(), Order::incorporateOrder)
            .get();

        order.setOrderId(orderId);

        return order;
    }

    /**
     * Adds a order event details for the authenticated user
     *
     * @param orderEvent, is the event detailing the action performed by the user
     * @return Response, indicating whether the result was a success
     * @throws OrderException for orderEvent() if it is any error or failure in the operation
     */
    public Response<String> orderEvent(OrderEvent orderEvent) throws OrderException{
        Response<String> response = new Response<String>();
        OrderEventType type = OrderEventType.getValueOf((orderEvent!=null&&orderEvent.getOrderEventType()!=null)?orderEvent.getOrderEventType().toString():"");
        if(null!= type && null!=orderEvent && null!= orderEvent.getOrderId()
                        && !orderEvent.getOrderId().trim().isEmpty()) {
            orderEventRep.save(orderEvent);
            response.setContent(orderEvent.getOrderId());
            response.setStatus(StatusCode.U200);
            return response;
        }
        response.setStatus(StatusCode.E400);
        return response;
    }

    /**
     * This cartCheckOut() is called once the user is submit the cart checkout
     *
     * @param shoppingCart, is the shopping cart of an aggregate object derived from events performed by the user
     * @param orderId, unique order id for cart checkout
     * @return Response, indicating whether the result was a success which is saved as order events
     * @throws OrderException for cartCheckout() if it is any error or failure in the operation
     */
    public Response<String> cartCheckout(ShoppingCart shoppingCart, String orderId) throws OrderException {
        if(null!=shoppingCart) {
            OrderEvent orderEvent = orderCreate(orderId, shoppingCart, shoppingCart.getCartId() != null ? shoppingCart.getCartId() : "");
            return orderEvent(orderEvent);
        }
        Response<String> response = new Response<String>();
        response.setStatus(StatusCode.E400);
        return response;

    }

    private OrderEvent orderCreate(String orderId, ShoppingCart shoppingCart, String cartId) {

        return new OrderEvent(orderId, OrderEventType.CREATED, shoppingCart, cartId);

    }

    /**
     *<p> This checkCartExists() accepts shoppingCartId as input and returns respective orderEvent from database
     * if not exist in database thn creates new Order Id
     * </p>
     * @param shoppingCartId which use to generate orderId
     * @return orderId, unique order id for cart checkout
     * @throws OrderException for checkCartExists() if it is any error or failure in the operation
     */
    //check cart is exists - returns/create-> orderId
    public String checkCartExists(String shoppingCartId) throws OrderException {

        OrderEvent orderEvent = orderEventRep.findOrderEventByCartId(shoppingCartId, OrderEventType.CREATED.toString());
        String orderId = null;
        if(null!= orderEvent && !orderEvent.getOrderId().trim().isEmpty()){
            orderId = orderEvent.getOrderId();
        } else {
            orderId = UUID.randomUUID().toString();
            orderEvent(orderCreate(orderId, null, shoppingCartId));
        }
        return orderId;
    }

    /*public List<Order> getOrderHistory(List<String> orderIds) throws OrderException{

        List<Order> orders = new ArrayList<Order>();
        for(String orderId: orderIds) {
            Order order = orderSummary(orderId);

            orders.add(order);
        }
        return orders;
    }*/
}
