package com.centurylink.pctl.mod.order.controllers.rest;

import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.user.CartInfo;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.order.domain.order.Order;
import com.centurylink.pctl.mod.order.domain.order.OrderException;
import com.centurylink.pctl.mod.order.domain.order.PctlOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class PctlOrderRestController is a RestController using springMVC
 * <br>
 * This class PctlOrderRestController contains three methods
 * <br> - cartCheckout()
 * <br> - orderEvent()
 * <br> - orderSummary()
 *
 * @author veeraragavan.k
 */
@RequestMapping("/order")
@RestController
public class PctlOrderRestController {

    private static final Logger LOG = LoggerFactory.getLogger(PctlOrderRestController.class);

    @Autowired
    public PctlOrderService pctlOrderService;

    /**
     * <p>cartCheckout() - it generates the unique Order Id while doing checkout in cart event
     * if an Order id is already created for the given cart then it returns existing orderId, else
     * generates new orderId.
     * </p>
     * @param cartInfo, contains info on cart id and cart state
     * @return  Response, returns orderid with status code
     *
     */
    @RequestMapping(value="/cart-checkout", method = RequestMethod.POST)
    public ResponseEntity<Response<String>> cartCheckout(@RequestBody CartInfo cartInfo) {

        LOG.info("Cart-Checkout for initializing the Order  ");
        Response<String> response = new Response<String>();
        String orderId = null;
        try {
            if (null != cartInfo && null != cartInfo.getShoppingCartId() && !cartInfo.getShoppingCartId().trim().isEmpty()) {
                orderId = pctlOrderService.checkCartExists(cartInfo.getShoppingCartId());
                response.setContent(orderId);
                response.setStatus(StatusCode.E800);
            } else {
                LOG.info("Error in Cart-Checkout for initializing the Order  ");
                response.setStatus(StatusCode.U400);
            }
        }catch (OrderException exception) {
            response.setStatus(StatusCode.E803);
            response.addMessage(exception.getMessage());
        }
        return new ResponseEntity<Response<String>>(response,response.getHttpStatus());
    }

    /**
     * post method which receives order events, if it valid event then persist it .
     * @param orderEvent {@link com.centurylink.pctl.mod.core.model.order.OrderEvent}
     * @return Response, orderId with status code and message
     */
    @RequestMapping(value="/events", method = RequestMethod.POST)
    public ResponseEntity<Response<String>> orderEvent(@RequestBody OrderEvent orderEvent) {

        LOG.info("Order events call");
        Response<String> response = new Response<String>();
        try {
            response = pctlOrderService.orderEvent(orderEvent);
        }catch (OrderException exception) {
            response.setStatus(StatusCode.E803);
            response.addMessage(exception.getMessage());
        }
        return new ResponseEntity<Response<String>>(response, response.getHttpStatus());
    }

    /**
     * This orderSummary() gets parameter as orderId, which is submitted on cart-checkout
     * @param orderId, one of the available placed order id
     * @return Response of Order details
     */
    @RequestMapping(value="/summary/{orderId}")
    public ResponseEntity<Response<Order>> orderSummary(@PathVariable("orderId") String orderId) {

        LOG.info("Order summary called");
        Response<Order> response = new Response<Order>();
        try{
            if(null!=orderId && !orderId.trim().isEmpty()) {
                Order orderResponse =  pctlOrderService.orderSummary(orderId);
                /*response.setContent(orderResponse);
                response.setStatus(StatusCode.U200);*/
            if(null!=orderResponse.getShoppingCart()){
                response.setContent(orderResponse);
                response.setStatus(StatusCode.U200);
            }else {
                response.setStatus(StatusCode.E801);
            }
            }else {
                LOG.info("Order id is empty");
                response.setStatus(StatusCode.U400);
            }
        } catch(OrderException exception) {
            response.setStatus(StatusCode.E803);
            response.addMessage(exception.getMessage());
        }

        return new ResponseEntity<Response<Order>>(response,response.getHttpStatus());
    }

    /*//Order submit to POIM
    @RequestMapping(value="submit", method = RequestMethod.POST)
    public String submitOrder(@RequestBody OrderSubmitReq orderSubmitReq ) throws OrderException {


        return null;
    }*/

    //return list of orders for the respective order id
    /*@RequestMapping(value="/history", method = RequestMethod.POST)
    public List<Order> getOrderHistory(@RequestBody List<String> orderIds) {

        LOG.info("Get Order history details");
        List<Order> orders =null;
        try{
            orders = pctlOrderService.getOrderHistory(orderIds);
        } catch (OrderException exception) {
            LOG.info("Error in getting Order history");
        }
        return orders;
    }*/
}
