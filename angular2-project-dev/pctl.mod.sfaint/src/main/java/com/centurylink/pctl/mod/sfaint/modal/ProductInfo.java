package com.centurylink.pctl.mod.sfaint.modal;

import java.io.Serializable;

/**
 * Created by dakshinamoorthyd on 23-11-2016.
 */
public class ProductInfo  implements Serializable {
    private String  productCode;
    private String nrc;
    private String mrc;
    private String term;
    private String commitment;
    private String contractType;
    private String productType;
    private String previousMrc;

    public ProductInfo() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getMrc() {
        return mrc;
    }

    public void setMrc(String mrc) {
        this.mrc = mrc;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCommitment() {
        return commitment;
    }

    public void setCommitment(String commitment) {
        this.commitment = commitment;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPreviousMrc() {
        return previousMrc;
    }

    public void setPreviousMrc(String previousMrc) {
        this.previousMrc = previousMrc;
    }


    @Override
    public String toString() {
        return "ProductInfo{" +
            "productCode='" + productCode + '\'' +
            ", nrc='" + nrc + '\'' +
            ", mrc='" + mrc + '\'' +
            ", term='" + term + '\'' +
            ", commitment='" + commitment + '\'' +
            ", contractType='" + contractType + '\'' +
            ", productType='" + productType + '\'' +
            ", previousMrc='" + previousMrc + '\'' +
            '}';
    }
}
