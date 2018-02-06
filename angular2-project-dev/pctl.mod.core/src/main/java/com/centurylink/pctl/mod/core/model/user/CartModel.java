package com.centurylink.pctl.mod.core.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sakthivel-s on 08-11-2016.
 */
public class CartModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String fingerPrint;
    private String cartId;

    public CartModel() {
    }

    public CartModel(@JsonProperty("username") String username,
                     @JsonProperty("fingerPrint") String fingerPrint,
                     @JsonProperty("cartId") String cartId) {
        this.login = username;
        this.fingerPrint = fingerPrint;
        this.cartId = cartId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
