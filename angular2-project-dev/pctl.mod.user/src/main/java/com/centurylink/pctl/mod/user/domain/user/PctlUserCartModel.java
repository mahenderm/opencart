package com.centurylink.pctl.mod.user.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sakthivel-s on 08-11-2016.
 */
public class PctlUserCartModel {

    private String login;
    private String fingerPrint;
    private String cartId;

    public PctlUserCartModel() {
    }

    public PctlUserCartModel(@JsonProperty("username") String username,
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
