package com.centurylink.pctl.mod.core.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Price Bean class
 *
 * @author Begin.samuel
 */
@Document(collection = "products_price")
public class Price extends ProductVariants implements Serializable {

    /**
     * priceid of Product
     */
    @Id
    private String _id;

    /**
     * currency of Product
     */
    private String currency;

    /**
     * id of Product
     *
     * @return id
     */
    public String get_id() {
        return _id;
    }

    /**
     * set method for price id of Product
     *
     * @param _id
     */
    public void set_id(String _id) {
        this._id = _id;
    }


    /**
     * currency details of Product price information
     *
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * set method of currency details of price product
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * PriceInfo of product as list
     *
     * @return priceList
     */

    public List<PriceInfo> getPriceList() {
        return priceList;
    }

    /**
     * set method for list of PriceInfo
     *
     * @param priceList
     */
    public void setPriceList(List<PriceInfo> priceList) {
        this.priceList = priceList;
    }

    /**
     * priceList as list of PriceInfo
     */
    private List<PriceInfo> priceList;

    /**
     * MRC field of product
     */
    @JsonIgnore
    private String pricemrc;
    /**
     * NRC field of product
     */
    @JsonIgnore
    private String pricenrc;
    /**
     * getpriceMrc(), getter methdo for MRC of product
     * @return pricemrc
     */
    @JsonIgnore
    public String getpriceMrc() {
        return pricemrc;
    }
    /**
     * setpriceMrc(),setter method of MRC
     * @param pricemrc
     */
    public void setpriceMrc(String pricemrc) {
        this.pricemrc = "10";
    }
    /**
     * getpriceNrc(), getter methdo for NRC of product
     * @return pricenrc
     */
    @JsonIgnore
    public String getpriceNrc() {
        return pricenrc;
    }
    /**
     * setpriceNrc(),setter method of NRC
     * @param pricenrc
     */
    public void setpriceNrc(String pricenrc) {
        this.pricenrc = "10";
    }
}

