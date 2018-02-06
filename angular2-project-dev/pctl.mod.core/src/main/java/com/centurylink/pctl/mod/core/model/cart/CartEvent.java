package com.centurylink.pctl.mod.core.model.cart;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

/**
 * CartEvent Bean
 *
 * @author nagavenkatakirang
 */
@Document(collection = "cart_events")
public class CartEvent implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * id of CartEvent
     */
    @Id
    private String id;
    /**
     * cartId of CartEvent
     */
    @NotEmpty(message = "cartId is not Provided")
    private String cartId;
    /**
     * cartEventType of CartEvent
     */
    //@NotEmpty(message = "cartEventType is not Provided")
    private CartEventType cartEventType;
    /**
     * productId of CartEvent
     */
    private String productId;
    /**
     * quantity of CartEvent
     */
    private Integer quantity;
    /**
     * location of CartEvent
     */
    private AddressLocation location;
    /**
     * productName of CartEvent
     */
    private String productName;
    /**
     * productTemplateName of CartEvent
     */
    private String productTemplateName;
    /**
     * productTemplateId of CartEvent
     */
    private String productTemplateId;
    /**
     * unitPrice of CartEvent
     */
    private String unitPrice;
    /**
     * term of LineItem
     */
    private String term;
    //constructors
    public CartEvent() {}

    public CartEvent(String id, CartEventType cartEventType, String productId, Integer quantity) {
        this.id = id;
        this.cartEventType = cartEventType;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartEvent(CartEventType cartEventType) {
        this.cartEventType = cartEventType;
    }

    public CartEvent(CartEventType cartEventType, String productId, Integer quantity) {
        this.cartEventType = cartEventType;
        this.productId = productId;
        this.quantity = quantity;
    }
    //getters and setters
    /**
     * getter for productName
     * @return productName
     */
    public String getProductName() {
        return productName;
    }
    /**
     * setter for productName
     * @param productName name of the product
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * getter for productTemplateName
     * @return productTemplateName
     */
    public String getProductTemplateName() {
        return productTemplateName;
    }
    /**
     * setter for productTemplateName
     * @param productTemplateName name of the productTemplate
     */
    public void setProductTemplateName(String productTemplateName) {
        this.productTemplateName = productTemplateName;
    }
    /**
     * getter for productTemplateId
     * @return productTemplateId
     */
    public String getProductTemplateId() {
        return productTemplateId;
    }
    /**
     * setter for productTemplateId
     * @param productTemplateId productTemplateId of the cartEvent
     */
    public void setProductTemplateId(String productTemplateId) {
        this.productTemplateId = productTemplateId;
    }
    /**
     * getter for unitPrice
     * @return unitPrice
     */
    public String getUnitPrice() {
        return unitPrice;
    }
    /**
     * setter for unitPrice
     * @param unitPrice price of the product
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    /**
     * getter for cartId
     * @return cartId
     */
    public String getCartId() {
        return cartId;
    }
    /**
     * setter for cartId
     * @param cartId cart id
     */
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    /**
     * getter for id
     * @return id
     */
    public String getId() {
        return id;
    }
    /**
     * setter for id
     * @param id id of the cartEvent
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * getter for cartEventType
     * @return cartEventType
     */
    public CartEventType getCartEventType() {
        return cartEventType;
    }
    /**
     * setter for cartEventType
     * @param cartEventType type of the cartEvent
     */
    public void setCartEventType(CartEventType cartEventType) {
        this.cartEventType = cartEventType;
    }
    /**
     * getter for productId
     * @return productId
     */
    public String getProductId() {
        return productId;
    }
    /**
     * setter for productId
     * @param productId id of the product
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * getter for quantity
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }
    /**
     * setter for quantity
     * @param quantity quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    /**
     * getter for location
     * @return location
     */
    public AddressLocation getLocation() {
        return location;
    }
    /**
     * setter for location
     * @param location location details
     */
    public void setLocations(AddressLocation location) {
        this.location = location;
    }
    /**
     * getter for term
     * @return term
     */
    public String getTerm() {
        return term;
    }
    /**
     * setter for term
     * @param term term of the product
     */

    public void setTerm(String term) {
        this.term = term;
    }
    /**
     * toString() method is overridden to return the state of the CartEvent
     */
    @Override
    public String toString() {
        return "CartEvent{" +
            "id=" + id +
            ", cartEventType=" + cartEventType +
            ", productId='" + productId + '\'' +
            ", quantity=" + quantity +
            '}';
    }
}
