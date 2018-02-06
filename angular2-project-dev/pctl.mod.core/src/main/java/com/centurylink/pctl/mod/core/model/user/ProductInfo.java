package com.centurylink.pctl.mod.core.model.user;

import java.io.Serializable;

/**
 * Created by sakthivel-s on 28-11-2016.
 */
public class ProductInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
 private String product;
 private int locationSize;
 private String unitPrice;


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getLocationSize() {
    return locationSize;
}

    public void setLocationSize(int locationSize) {
        this.locationSize = locationSize;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
