package com.centurylink.pctl.mod.order.domain.order;

import com.centurylink.pctl.mod.core.utils.StatusCode;

/**
 * This OrderException class which extends Exception class
 * <br>
 * <p> This is the customized exception class which used to handle the errors and exception
 * in the Order Service API
 * </p>
 * @author veeraragavan.k
 */
public class OrderException extends Exception{

    private String orderId;
    private String errorCode;
    private String errorMessage;

    public OrderException(String orderId, StatusCode errorCode, String message) {
        super(message);
        this.orderId = orderId;
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage()+message;
    }
}
