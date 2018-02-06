package com.centurylink.pctl.mod.core.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * LineItem Bean
 *
 * @author nagavenkatakirang
 */
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * _id of LineItem
     */
    @JsonIgnore
    private String _id;
    /**
     * productId of LineItem
     */
    private String productId;
    /**
     * quantity of LineItem
     */
    private Integer quantity;
    /**
     * productName of LineItem
     */
    private String productName;
    /**
     * productTemplateName of LineItem
     */
    private String productTemplateName;
    /**
     * productTemplateId of LineItem
     */
    private String productTemplateId;
    /**
     * unitPrice of LineItem
     */
    private String unitPrice;
    /**
     * locations of LineItem
     */
    private List<AddressLocation> locations = new ArrayList<>();
    /**
     * term of LineItem
     */
    private String term;
    //constructors
    public LineItem(){

    }
    public LineItem(String term,String _id,String productId, Integer quantity, String productName, String productTemplateName, String productTemplateId, String unitPrice, List<AddressLocation> locations) {
        this.term=term;
        this._id = _id;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productTemplateName = productTemplateName;
        this.productTemplateId = productTemplateId;
        this.unitPrice = unitPrice;
        this.locations = locations;
    }


    public LineItem(String _id, String productId, String productName, String productTemplateName, String productTemplateId, String unitPrice, Integer quantity) {
        this._id = _id;
        this.productId = productId;
        this.productName = productName;
        this.productTemplateName = productTemplateName;
        this.productTemplateId = productTemplateId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public LineItem(String _id, String productId, String productName, String productTemplateName, String productTemplateId, String unitPrice) {
        this._id = _id;
        this.productId = productId;
        this.productName = productName;
        this.productTemplateName = productTemplateName;
        this.productTemplateId = productTemplateId;
        this.unitPrice = unitPrice;
    }

    public LineItem(String _id, String productId, Integer quantity, String productName, String productTemplateName, String productTemplateId, String unitPrice, List<AddressLocation> locations) {
        this._id = _id;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productTemplateName = productTemplateName;
        this.productTemplateId = productTemplateId;
        this.unitPrice = unitPrice;
        this.locations = locations;
    }
    /**
     * getter for _id
     * @return _id
     */
    public String get_id() {
        return _id;
    }
    /**
     * setter for _id
     * @param _id _id
     */
    public void set_id(String _id) {
        this._id = _id;
    }
    /**
     * getter for locations
     * @return locations
     */
    public List<AddressLocation> getLocations() {
        return locations;
    }
    /**
     * setter for locations
     * @param locations list of locations
     */
    public void setLocations(List<AddressLocation> locations) {
        this.locations = locations;
    }
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
     * @param productTemplateName template name of the product
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
     * @param productTemplateId template id of the product
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
     * getter for term
     * @return term
     */
    public String getTerm() {
        return term;
    }
    /**
     * setter for term
     * @param term term in months
     */

    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * toString() method is overridden to return the state of the LineItem
     */
    @Override
    public String toString() {
        return "LineItem{" +
            "_id='" + _id + '\'' +
            ", productId='" + productId + '\'' +
            ", quantity=" + quantity +
            ", productName='" + productName + '\'' +
            ", productTemplateName='" + productTemplateName + '\'' +
            ", productTemplateId='" + productTemplateId + '\'' +
            ", unitPrice='" + unitPrice + '\'' +
            ", term='" + term + '\'' +
            ", locations=" + locations +
            '}';
    }

}
