package com.centurylink.pctl.mod.core.model.order;

import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by veeraragavan.k on 07-11-2016.
 */
@Document(collection = "order_event")
public class OrderEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String cartId;
    private String orderId;
    private OrderEventType orderEventType;
    private Address billingAddress;
    private String companyName;
    private ShoppingCart shoppingCart;
    private Address hqAddress;
    private BillingContactDetails billingContactDetails;
    private Date orderDate;

    public OrderEvent() {
    }

    public OrderEvent(String orderId, OrderEventType orderEventType, ShoppingCart shoppingCart, String cartId) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.orderEventType = orderEventType;
        this.shoppingCart = shoppingCart;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderEventType getOrderEventType() {
        return orderEventType;
    }

    public void setOrderEventType(OrderEventType orderEventType) {
        this.orderEventType = orderEventType;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Address getHqAddress() {
        return hqAddress;
    }

    public void setHqAddress(Address hqAddress) {
        this.hqAddress = hqAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BillingContactDetails getBillingContactDetails() {
        return billingContactDetails;
    }

    public void setBillingContactDetails(BillingContactDetails billingContactDetails) {
        this.billingContactDetails = billingContactDetails;
    }


    public static OrderEventType getValueOf(String name) {
        for (OrderEventType type : OrderEventType.values()) {
            if (type.name().equalsIgnoreCase(name))
                return type;
        }
        return null;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
            "id='" + id + '\'' +
            ", orderId='" + orderId + '\'' +
            ", orderEventType=" + orderEventType +
            ", billingAddress=" + billingAddress +
            ", companyName=" + companyName +
            '}';
    }
}
