package com.centurylink.pctl.mod.core.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * ProductVariants Bean class
 *
 * @author Begin.samuel
 */
public class ProductVariants implements Serializable {


    /**
     * haServiceVendor of Product
     */
    private String haServiceVendor;
    /**
     * securityPkg of Product
     */
    private String securityPkg;
    /**
     * displayImage of Product
     */
    private String displayImage;
    /**
     * serviceVendor of Product
     */
    private String serviceVendor;
    /**
     * tier1Support of Product
     */
    private String tier1Support;
    /**
     * haSecurityPkg of Product
     */
    private String haSecurityPkg;
    /**
     * serviceTransport of Product
     */
    private String serviceTransport;
    /**
     * variantId of Product
     */
    private String variantId;
    /**
     * sku of Product
     */
    private String sku;
    /**
     * productId of Product
     */
    private String productId;
    /**
     * descriptionHtml of Product
     */
    private String descriptionHtml;
    /**
     * updatedAt of Product
     */
    private String updatedAt;
    /**
     * displayPosition of Product
     */
    private String displayPosition;
    /**
     * haCpe of Product
     */
    private String haCpe;
    /**
     * haServicePlan of Product
     */
    private String haServicePlan;
    /**
     * createdAt of Product
     */
    private String createdAt;
    /**
     * name of Product
     */
    private String name;
    /**
     * servicePlan of Product
     */
    private String servicePlan;
    /**
     * haServiceTransport of Product
     */
    private String haServiceTransport;

    /**
     * mrc , MRC details of product
     */
    @JsonIgnore
    private List<String> mrc;

    /**
     * nrc, NRC details of product
     */
    @JsonIgnore
    private List<String> nrc;
    /**
     * MRC CPE details of product
     */
    @JsonIgnore
    private List<String> mrcCpe;
    /**
     * NRC CPE detail of product
     */
    @JsonIgnore
    private List<String> nrcCpe;

    /**
     * standardCpe of the ProductVariants
     *
     * @return standardCpe
     */
    public String getStandardCpe() {
        return standardCpe;
    }

    /**
     * set method for standardCpe of ProductVariants
     * @param standardCpe
     */
    public void setStandardCpe(String standardCpe) {
        this.standardCpe = standardCpe;
    }

    private String standardCpe;

    /**
     * haServiceVendor of the ProductVariants
     *
     * @return haServiceVendor
     */
    public String getHaServiceVendor() {
        return haServiceVendor;
    }

    /**
     * set method for haServiceVendor of ProductVariants
     * @param haServiceVendor
     */
    public void setHaServiceVendor(String haServiceVendor) {
        this.haServiceVendor = haServiceVendor;
    }

    /**
     * securityPkg of the ProductVariants
     *
     * @return securityPkg
     */
    public String getSecurityPkg() {
        return securityPkg;
    }

    /**
     * set method for securityPkg of ProductVariants
     * @param securityPkg
     */
    public void setSecurityPkg(String securityPkg) {
        this.securityPkg = securityPkg;
    }

    /**
     * displayImage of the ProductVariants
     *
     * @return displayImage
     */
    public String getDisplayImage() {
        return displayImage;
    }

    /**
     * set method for displayImage of ProductVariants
     * @param displayImage
     */
    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    /**
     * serviceVendor of the ProductVariants
     *
     * @return serviceVendor
     */
    public String getServiceVendor() {
        return serviceVendor;
    }

    /**
     * set method for serviceVendor of ProductVariants
     * @param serviceVendor
     */
    public void setServiceVendor(String serviceVendor) {
        this.serviceVendor = serviceVendor;
    }

    /**
     * tier1Support of the ProductVariants
     *
     * @return tier1Support
     */
    public String getTier1Support() {
        return tier1Support;
    }

    /**
     * set method for tier1Support of ProductVariants
     * @param tier1Support
     */
    public void setTier1Support(String tier1Support) {
        this.tier1Support = tier1Support;
    }

    /**
     * haSecurityPkg of the ProductVariants
     *
     * @return haSecurityPkg
     */
    public String getHaSecurityPkg() {
        return haSecurityPkg;
    }

    /**
     * set method for haSecurityPkg of ProductVariants
     * @param haSecurityPkg
     */
    public void setHaSecurityPkg(String haSecurityPkg) {
        this.haSecurityPkg = haSecurityPkg;
    }

    /**
     * serviceTransport of the ProductVariants
     *
     * @return serviceTransport
     */
    public String getServiceTransport() {
        return serviceTransport;
    }

    /**
     * set method for serviceTransport of ProductVariants
     * @param serviceTransport
     */
    public void setServiceTransport(String serviceTransport) {
        this.serviceTransport = serviceTransport;
    }

    /**
     * variantId of the ProductVariants
     *
     * @return variantId
     */
    public String getVariantId() {
        return variantId;
    }

    /**
     * set method for variantId of ProductVariants
     * @param variantId
     */
    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    /**
     * sku of the ProductVariants
     *
     * @return sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * set method for sku of ProductVariants
     * @param sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * productId of the ProductVariants
     *
     * @return productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * set method for productId of ProductVariants
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * descriptionHtml of the ProductVariants
     *
     * @return descriptionHtml
     */
    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    /**
     * set method for descriptionHtml of ProductVariants
     * @param descriptionHtml
     */
    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    /**
     * updatedAt of the ProductVariants
     *
     * @return updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * set method for updatedAt of ProductVariants
     * @param  updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * displayPosition of the ProductVariants
     *
     * @return displayPosition
     */
    public String getDisplayPosition() {
        return displayPosition;
    }

    /**
     * set method for displayPosition of ProductVariants
     * @param displayPosition
     */
    public void setDisplayPosition(String displayPosition) {
        this.displayPosition = displayPosition;
    }

    /**
     * haCpe of the ProductVariants
     *
     * @return haCpe
     */
    public String getHaCpe() {
        return haCpe;
    }

    /**
     * set method for haCpe of ProductVariants
     * @param haCpe
     */
    public void setHaCpe(String haCpe) {
        this.haCpe = haCpe;
    }

    /**
     * haServicePlan of the ProductVariants
     *
     * @return haServicePlan
     */
    public String getHaServicePlan() {
        return haServicePlan;
    }

    /**
     * set method for haServicePlan of ProductVariants
     * @param haServicePlan
     */
    public void setHaServicePlan(String haServicePlan) {
        this.haServicePlan = haServicePlan;
    }

    /**
     * createdAt of the ProductVariants
     *
     * @return createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * set method for createdAt of ProductVariants
     * @param createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * name of the ProductVariants
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set method for name of ProductVariants
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * servicePlan of the ProductVariants
     *
     * @return servicePlan
     */
    public String getServicePlan() {
        return servicePlan;
    }

    /**
     * set method for servicePlan of ProductVariants
     * @param servicePlan
     */
    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    /**
     * haServiceTransport of the ProductVariants
     *
     * @return haServiceTransport
     */
    public String getHaServiceTransport() {
        return haServiceTransport;
    }

    /**
     * set method for haServiceTransport of ProductVariants
     * @param haServiceTransport
     */
    public void setHaServiceTransport(String haServiceTransport) {
        this.haServiceTransport = haServiceTransport;
    }

    /**
     *  getMrc() method returns mrc
      * @return mrc
     */
    @JsonIgnore
    public List<String> getMrc() {
        return mrc;
    }
    /**
     * setMrc() method for MRC
     * @param mrc
     */
    public void setMrc(List<String> mrc) {
        this.mrc = mrc;
    }
    /**
     *  NRC, setter method
     * @return nrc
     */
    @JsonIgnore
    public List<String> getNrc() {
        return nrc;
    }
    /**
     * NRC, setter method
     * @param nrc
     */
    public void setNrc(List<String> nrc) {
        this.nrc = nrc;
    }
    /**
     * getter method for MRC CPE
     * @return mrcCpe
     */
    @JsonIgnore
    public List<String> getMrcCpe() {
        return mrcCpe;
    }
    /**
     * setMrcCpe() setter method for MRC CPE
     * @param mrcCpe
     */
    public void setMrcCpe(List<String> mrcCpe) {
        this.mrcCpe = mrcCpe;
    }
    /**
     * getter method for NRC CPE
     * @return nrccpe
     */
    @JsonIgnore
    public List<String> getNrcCpe() {
        return nrcCpe;
    }
    /**
     * setNrcCpe() setter method for NRC CPE
     * @param nrcCpe
     */
    public void setNrcCpe(List<String> nrcCpe) {
        this.nrcCpe = nrcCpe;
    }
    /**
     * toString() method is overridden
     */
    @Override
    public String toString() {
        return "ProductVariants{" +
            "haServiceVendor='" + haServiceVendor + '\'' +
            ", securityPkg='" + securityPkg + '\'' +
            ", displayImage='" + displayImage + '\'' +
            ", serviceVendor='" + serviceVendor + '\'' +
            ", tier1Support='" + tier1Support + '\'' +
            ", haSecurityPkg='" + haSecurityPkg + '\'' +
            ", serviceTransport='" + serviceTransport + '\'' +
            ", variantId='" + variantId + '\'' +
            ", sku='" + sku + '\'' +
            ", productId='" + productId + '\'' +
            ", descriptionHtml='" + descriptionHtml + '\'' +
            ", updatedAt='" + updatedAt + '\'' +
            ", displayPosition='" + displayPosition + '\'' +
            ", haCpe='" + haCpe + '\'' +
            ", haServicePlan='" + haServicePlan + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", name='" + name + '\'' +
            ", servicePlan='" + servicePlan + '\'' +
            ", haServiceTransport='" + haServiceTransport + '\'' +
            ", mrc=" + mrc +
            ", nrc=" + nrc +
            ", mrcCpe=" + mrcCpe +
            ", nrcCpe=" + nrcCpe +
            ", standardCpe='" + standardCpe + '\'' +
            '}';
    }
}

