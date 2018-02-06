package com.centurylink.pctl.mod.order.domain.order;

import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.model.cart.ShoppingCart;
import com.centurylink.pctl.mod.core.model.order.BillingContactDetails;
import com.centurylink.pctl.mod.core.model.order.OrderEvent;
import com.centurylink.pctl.mod.core.model.order.OrderEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Stream;

/**
 * A simple domain class for the {@link Order} concept in the order context.
 *
 * @author veeraragavan.k
 */
@Document(collection = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(Order.class);
    @Id
    private String id;
    private String cartId;
    private String orderId;
    private OrderStatus orderStatus;
    private Address billingAddress;
    private String companyName;
    private ShoppingCart shoppingCart;
    private Address hqAddress;
    private BillingContactDetails billingContactDetails;
    private Date orderCreatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(String orderId, OrderStatus orderStatus, Address billingAddress, String companyName) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.billingAddress = billingAddress;
        this.companyName = companyName;
    }

    public String getCartId() {

        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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

    public BillingContactDetails getBillingContactDetails() {
        return billingContactDetails;
    }

    public void setBillingContactDetails(BillingContactDetails billingContactDetails) {
        this.billingContactDetails = billingContactDetails;
    }

    public Date getOrderCreatedDate() {
        return orderCreatedDate;
    }

    public void setOrderCreatedDate(Date orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }


    /**
     * The incorporate method uses a simple state machine for an order's status to generate
     * the order and its current state of an order using event sourcing and aggregation.
     * <p>
     * The event diagram below represents how events are incorporated into generating the
     * order status. The event log for the order status can be used to rollback the state
     * of the order in the case of a failed distributed transaction.
     *
     * @param orderEvent is the event to incorporate into the state machine
     * @return the aggregate {@link Order} with the aggregated order status
     */
    public Order incorporateOrder(OrderEvent orderEvent) {

        log.info("Order Event {} ", orderEvent);
        Flux<OrderEventType> validOrderEventTypes = Flux.fromStream(Stream.of(OrderEventType.PURCHASED,
                                                       OrderEventType.CREATED, OrderEventType.UPDATED,
                                                       OrderEventType.ORDERED ));

        if (validOrderEventTypes.exists(orderEventType ->
                orderEvent.getOrderEventType().equals(orderEventType)).get()) {


            this.billingAddress = orderEvent.getBillingAddress()!=null?orderEvent.getBillingAddress():this.billingAddress;
            this.companyName = orderEvent.getCompanyName()!=null?orderEvent.getCompanyName():this.companyName;
            this.shoppingCart = orderEvent.getShoppingCart()!=null?orderEvent.getShoppingCart():this.shoppingCart;
            this.hqAddress = orderEvent.getHqAddress()!=null?orderEvent.getHqAddress():this.hqAddress;
            this.billingContactDetails = orderEvent.getBillingContactDetails()!=null?orderEvent.getBillingContactDetails():this.billingContactDetails;


        }
        // setting order status based on order event
        settingOrderStatus(orderEvent);

        return this;
    }
    private void settingOrderStatus(OrderEvent orderEvent) {
        if(orderStatus==null) {
            orderStatus = OrderStatus.PURCHASED;
        }
        switch (orderStatus) {
            case PURCHASED:
                if(orderEvent.getOrderEventType()== OrderEventType.CREATED ||
                    orderEvent.getOrderEventType()==OrderEventType.PURCHASED||
                    orderEvent.getOrderEventType()==OrderEventType.UPDATED){
                    orderStatus = orderStatus.PENDING;
                }
                break;
            case PENDING:
                if (orderEvent.getOrderEventType()==OrderEventType.ORDERED) {
                    orderStatus = OrderStatus.CONFIRMED;
                }else if(orderEvent.getOrderEventType()==OrderEventType.PURCHASED) {
                    orderStatus = OrderStatus.PURCHASED;
                }
                break;
            case CONFIRMED:
                if(orderEvent.getOrderEventType()==OrderEventType.SHIPPED) {
                    orderStatus = orderStatus.SHIPPED;
                }else if(orderEvent.getOrderEventType()==OrderEventType.CREATED) {
                    orderStatus = OrderStatus.PENDING;
                }
                break;
            case SHIPPED:
                if (orderEvent.getOrderEventType() == OrderEventType.DELIVERED) {
                    orderStatus = OrderStatus.DELIVERED;
                } else if (orderEvent.getOrderEventType() == OrderEventType.ORDERED) {
                    orderStatus = OrderStatus.CONFIRMED;
                }
                break;
            case DELIVERED:
                if (orderEvent.getOrderEventType() == OrderEventType.SHIPPED)
                    orderStatus = OrderStatus.SHIPPED;
                break;
            default:
                break;
        }
    }
    @Override
    public String toString() {
        return "Order{" +
            "id='" + id + '\'' +
            ", cartId='" + cartId + '\'' +
            ", orderId='" + orderId + '\'' +
            ", orderStatus=" + orderStatus +
            ", billingAddress=" + billingAddress +
            ", companyName=" + companyName +
            '}';
    }
}
