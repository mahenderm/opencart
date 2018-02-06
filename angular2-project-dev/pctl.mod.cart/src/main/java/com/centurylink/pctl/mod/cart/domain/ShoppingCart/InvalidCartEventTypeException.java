package com.centurylink.pctl.mod.cart.domain.ShoppingCart;

import com.centurylink.pctl.mod.core.utils.StatusCode;

/**
 * Created by nagavenkatakirang on 14-11-2016.
 */
public class InvalidCartEventTypeException extends  Exception {
    public String cartId;
    public String errorCode;
    public String errorMessage;

    /*public InvalidCartEventTypeException(Throwable e, StatusCode code) {
        super(e);
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage();
    }*/
    public InvalidCartEventTypeException(String message, StatusCode code) {
        super(message);
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage() + message;
        //this.cartId = cartId;
    }
}
