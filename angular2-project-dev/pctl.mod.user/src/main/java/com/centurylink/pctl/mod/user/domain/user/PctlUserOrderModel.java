package com.centurylink.pctl.mod.user.domain.user;

import com.centurylink.pctl.mod.core.model.user.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakthivel-s on 16-11-2016.
 */
public class PctlUserOrderModel {
    private String orderId;
    private String cartId;
    private List<ProductInfo> productInfo = new ArrayList<ProductInfo>();
    private String orderDate;

    public PctlUserOrderModel(@JsonProperty("orderId") String orderId,
                     @JsonProperty("cartId") String cartId,
                     @JsonProperty("orderDate") String orderDate,
                     @JsonProperty("productInfo") List<ProductInfo> productInfo) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.productInfo=productInfo;
        this.orderDate = orderDate;
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

    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
