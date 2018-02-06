package com.centurylink.pctl.mod.cart.domain.ShoppingCart;

import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.model.cart.CartEvent;
import com.centurylink.pctl.mod.core.model.cart.CartEventType;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.utils.Response;
import com.centurylink.pctl.mod.core.utils.StatusCode;
import com.centurylink.pctl.mod.core.utils.ValidateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


/**
 * The {@link com.centurylink.pctl.mod.cart.domain.ShoppingCart.PctlCartService} implements business logic for aggregating the state of
 * a user's actions represented by a sequence of {@link com.centurylink.pctl.mod.core.model.cart.CartEvent}. The generated aggregate
 * uses event sourcing to produce a {@link com.centurylink.pctl.mod.core.model.cart.ShoppingCart} containing a collection of
 * {@link com.centurylink.pctl.mod.core.model.cart.LineItem}.
 * <br>
 *     This class PctlCartService implements business logic for aggregating the state of a user's actions
 *     This class contains three business methods
 * <br> - addCartEvent(CartEvent)
 * <br> - getShoppingCart(...)
 * <br> - aggregateCartEvents(...)
 * @author nagavenkatakirang
 */
@Service
public class PctlCartService {
    /**
     * pctlCartEventRepository for storing cart events
     */
    @Autowired
    PctlCartEventRepository pctlCartEventRepository;

    //constructor
    public PctlCartService() {
    }

    /**
     * This method adds a cart event to the repository
     * @param cartEvent {@link com.centurylink.pctl.mod.core.model.cart.CartEvent}
     * @return a flag indicating whether the result was a success (event persisted or not)
     */
    public Boolean addCartEvent(CartEvent cartEvent) throws InvalidCartEventTypeException {
        /*ValidateUtils<CartEvent> validator = new ValidateUtils<>();
        Response response = validator.validateBean(cartEvent);
        */
        //Identify valid cart event type
        //CartEventType cartEventTypes = CartEventType.validate(type);
        CartEventType type=cartEvent.getCartEventType();
        CartEventType cartEventTypes = CartEventType.getValueOf(type.toString());
        if (cartEventTypes == null) {    //if invalid, appropriate response is set
            throw new InvalidCartEventTypeException("Invalid cart event type",StatusCode.E704);
        }
       // if (response != null) {
            pctlCartEventRepository.save(cartEvent);
                 return true;
        /*}else{
            throw new CartException("Invalid cart event",StatusCode.E702);
        }*/
    }

    /**
     * This getShoppingCart() method is used to retrieve the cart details by aggregating the cart events
     * @param cartId is used to retrieve the respective cart for that cartId
     * @return ShoppingCart, respective Cart for the valid cartId
     * @exception com.centurylink.pctl.mod.cart.domain.ShoppingCart.CartException cart exception is raised to handle the failure while retrieving the cart
     */
    public ShoppingCart getShoppingCart(String cartId) throws CartException {
        ShoppingCart shoppingCart = null;
        if (cartId != null) {
            //call aggregateCartEvents for aggregation
            shoppingCart = aggregateCartEvents(cartId);
            if (shoppingCart.getLineItems().size() == 0) {
                throw new CartException("Shopping Cart is empty!!! ", StatusCode.E705);
            }
        }
        return shoppingCart;
    }
    /**
     * Aggregate the cart events of a user and return a {@link com.centurylink.pctl.mod.core.model.cart.ShoppingCart} object
     *
     * @param cartId is the cart's Id to retrieve the shopping cart for
     * @return a shopping cart representing the aggregate state of the user's cart
     * @throws com.centurylink.pctl.mod.cart.domain.ShoppingCart.CartException for invalid input
     */
    private ShoppingCart aggregateCartEvents(String cartId) throws CartException {
        Flux<CartEvent> cartEvents = Flux.fromStream(pctlCartEventRepository.getCartEventStreamByCartId(cartId));
        // Aggregate the state of the shopping cart
        ShoppingCart shoppingCart = cartEvents
            .takeWhile(cartEvent -> !ShoppingCart.isTerminal(cartEvent.getCartEventType()))
            .reduceWith(() -> new ShoppingCart(), ShoppingCart::incorporateWithLocation)
            .get();
        shoppingCart.setCartId(cartId);
        shoppingCart.getLineItems();
        return shoppingCart;
    }
}
