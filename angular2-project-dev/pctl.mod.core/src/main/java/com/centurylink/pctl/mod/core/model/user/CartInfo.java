package com.centurylink.pctl.mod.core.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sakthivel-s on 01-11-2016.
 */

public class CartInfo implements Serializable {
    private static final long serialVersionUID = 1L;

  String  shoppingCartId;

    @JsonIgnore
    int cartState;

    @JsonIgnore
    public int getCartState() {

        return cartState;
    }

    public void setCartState(int cartState) {
        cartState = cartState;
    }



    public CartInfo(@JsonProperty("shoppingCartId") String shoppingCartId,
                   @JsonProperty("cartState") int cartState) {
        this.shoppingCartId = shoppingCartId;

        this.cartState = cartState;
    }



    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartInfo)) return false;
        CartInfo cartInfo = (CartInfo) o;
        if (cartState != cartInfo.cartState) return false;
        if (shoppingCartId != null ? !shoppingCartId.equals(cartInfo.shoppingCartId) : cartInfo.shoppingCartId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shoppingCartId != null ? shoppingCartId.hashCode() : 0;
        result = 31 * result + cartState;
        return result;
    }

    @Override
    public String toString() {
        return "CartInfo{" +
            "shoppingCartId='" + shoppingCartId + '\'' +
            ", cartState=" + cartState +
            '}';
    }
}
