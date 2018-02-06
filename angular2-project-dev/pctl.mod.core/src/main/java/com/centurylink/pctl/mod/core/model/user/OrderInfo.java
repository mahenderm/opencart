package com.centurylink.pctl.mod.core.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakthivel-s on 16-11-2016.
 */
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String cartId;
    private int state;

   private String orderDate ;
    private List<ProductInfo> productInfo = new ArrayList<ProductInfo>();

    public OrderInfo(@JsonProperty("orderId") String orderId,
                     @JsonProperty("cartId") String cartId,
                     @JsonProperty("state") int state,
                     @JsonProperty("orderDate") String orderDate,
                     @JsonProperty("productInfo") List<ProductInfo> productInfo) {

        this.orderId = orderId;
        this.state = state;
        this.cartId =cartId;
        this.orderDate = orderDate;
        this.productInfo = productInfo;
        }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderInfo)) return false;

        OrderInfo orderInfo = (OrderInfo) o;

        if (state != orderInfo.state) return false;
        if (cartId != null ? !cartId.equals(orderInfo.cartId) : orderInfo.cartId != null) return false;
        if (orderDate != null ? !orderDate.equals(orderInfo.orderDate) : orderInfo.orderDate != null) return false;
        if (orderId != null ? !orderId.equals(orderInfo.orderId) : orderInfo.orderId != null) return false;
        if (productInfo != null ? !productInfo.equals(orderInfo.productInfo) : orderInfo.productInfo != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (cartId != null ? cartId.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (productInfo != null ? productInfo.hashCode() : 0);
        return result;
    }
}
