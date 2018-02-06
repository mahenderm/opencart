package com.centurylink.pctl.mod.cart.domain.ShoppingCart;

import com.centurylink.pctl.mod.core.utils.StatusCode;

/**
 * CartException object will be thrown as an user defined exception for errors raised in the cart api module
 * @author nagavenkatakirang
 */
public class CartException extends Exception {
    //public String cartId;
    public String errorCode;
    public String errorMessage;

    public CartException(String message, StatusCode code) {
        super(message);
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage() + message;
        //this.cartId = cartId;
    }

    /*public CartException(Throwable e, StatusCode code, String cartId) {
        super(e);
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage();
        this.cartId = cartId;
    }*/
}
