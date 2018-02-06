package com.centurylink.pctl.mod.cart.controllers.rest;

import com.centurylink.pctl.mod.cart.domain.ShoppingCart.CartException;
import com.centurylink.pctl.mod.cart.domain.ShoppingCart.InvalidCartEventTypeException;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.cart.domain.ShoppingCart.PctlCartService;
import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import com.centurylink.pctl.mod.core.model.cart.CartEventType;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This class PctlCartRestController is a RestController using Spring MVC
 * <br>
 * This class contains two methods.
 * <br> - addCartEvent(...) to store the events to the repository and
 * <br> - getCartWithLocation(...) to get the Cart after aggregating the events.
 * @author nagavenkatakirang
 */
@RequestMapping("/cart")
@RestController
public class PctlCartRestController {


    private static final Logger log = LoggerFactory.getLogger(PctlCartRestController.class);

    @Autowired
    public PctlCartService pctlCartService;

    @Autowired
    public PctlCartRestController(PctlCartService shoppingCartService) {
        this.pctlCartService = shoppingCartService;
    }

    /**
     * POST method to persist the events.
     * @param cartId  cartId is passed as request parameter to add cart event
     * @param cartEventType event type request parameter contains the type of the event
     * @param cartEvent cartEvent is input
     * @return Response response is returned whether the event is persisted or not in the form of message.
     */
    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public Response addCartEvent(@RequestBody CartEvent cartEvent,
                                 @RequestParam String cartId,
                                 @RequestParam String cartEventType) {
        Response response = new Response();
        try {

            //Identify valid cart event type
            CartEventType cartEventTypes = CartEventType.getValueOf(cartEventType);
            if (cartEventTypes == null) {    //if invalid, appropriate response is set
                throw new InvalidCartEventTypeException("Invalid cart event type", StatusCode.E704);
            } else {
                cartEvent.setQuantity(1);
                cartEvent.setCartId(cartId);
                cartEvent.setCartEventType(cartEventTypes);
                pctlCartService.addCartEvent(cartEvent);
                response.setStatus(StatusCode.U200);    //appropriate response is set using StatusCode
            }
        }
        catch (InvalidCartEventTypeException e){
            response.setStatus(StatusCode.E704);
            response.addMessage(e.getMessage());
        }
        return response;    //appropriate response is returned
    }

    /**
     * GET method which fetches the cart by aggregating the events.
     * @param cartId  cartId is passed as parameter to get the cart.
     * @return Response ShoppingCart object (response) is returned.
     */
    @RequestMapping(path = "/shopping-cart/{cartId}", method = RequestMethod.GET)
    public Response<ShoppingCart> getCartWithLocation(@PathVariable String cartId) {
        Response<ShoppingCart> response = new Response<>();
        try {
                ShoppingCart shoppingCart = pctlCartService.getShoppingCart(cartId);
                response.setContent(shoppingCart);  //shoppingCart object is set in the response
        } catch (Exception e) {
            response.setStatus(StatusCode.E705);
            response.addMessage(e.getMessage());
        }
        return response;    //appropriate response is returned
    }

    public ShoppingCart getCheckOutCart( String cartId) {
        ShoppingCart shoppingCart = null;
        if (cartId != null) {
            try {
                shoppingCart = pctlCartService.getShoppingCart(cartId);
            } catch (CartException e) {
                e.printStackTrace();
            }
        }
        return shoppingCart;
    }

}
