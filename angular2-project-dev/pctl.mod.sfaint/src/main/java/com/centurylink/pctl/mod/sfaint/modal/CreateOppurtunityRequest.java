package com.centurylink.pctl.mod.sfaint.modal;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Bean class for Sfaint
 * @author Dinesh
 */
@Component
public class CreateOppurtunityRequest implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private String accountUUID;
    private String opptyName;
    private String opptyRecordType;
    private String subType;
    private String estimatedCloseDate;
    private String nrc;
    private String mrc;
    private String productCode;
    private String productName;
    private String stage;
    private List<ServiceLocation> serviceLocationList;
    private List<ProductInfo> productInfoList;

    public CreateOppurtunityRequest() {
    }

    public CreateOppurtunityRequest(String accountUUID, String opptyName, String opptyRecordType, String subType, String estimatedCloseDate, String nrc, String mrc, String productCode, String productName, String stage, List<ServiceLocation> serviceLocationList, List<ProductInfo> productInfoList) {
        this.accountUUID = accountUUID;
        this.opptyName = opptyName;
        this.opptyRecordType = opptyRecordType;
        this.subType = subType;
        this.estimatedCloseDate = estimatedCloseDate;
        this.nrc = nrc;
        this.mrc = mrc;
        this.productCode = productCode;
        this.productName = productName;
        this.stage = stage;
        this.serviceLocationList = serviceLocationList;
        this.productInfoList = productInfoList;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountUUID() {
        return accountUUID;
    }

    public void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    public String getOpptyRecordType() {
        return opptyRecordType;
    }

    public void setOpptyRecordType(String opptyRecordType) {
        this.opptyRecordType = opptyRecordType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }


    public List<ServiceLocation> getServiceLocationList() {
        return serviceLocationList;
    }

    public void setServiceLocationList(List<ServiceLocation> serviceLocationList) {
        this.serviceLocationList = serviceLocationList;
    }


    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public String getEstimatedCloseDate() {
        return estimatedCloseDate;
    }

    public void setEstimatedCloseDate(String estimatedCloseDate) {
        this.estimatedCloseDate = estimatedCloseDate;
    }

    public String getOpptyName() {
        return opptyName;
    }

    public void setOpptyName(String opptyName) {
        this.opptyName = opptyName;
    }

    @Override
    public String toString() {
        return "SfaintOPPRequest{" +
            "accountUUID='" + accountUUID + '\'' +
            ", opptyName='" + opptyName + '\'' +
            ", opptyRecordType='" + opptyRecordType + '\'' +
            ", subType='" + subType + '\'' +
            ", estimatedCloseDate='" + estimatedCloseDate + '\'' +
            ", nrc='" + nrc + '\'' +
            ", mrc='" + mrc + '\'' +
            ", productCode='" + productCode + '\'' +
            ", productName='" + productName + '\'' +
            ", stage='" + stage + '\'' +
            ", serviceLocationList=" + serviceLocationList +
            ", productInfoList=" + productInfoList +
            '}';
    }
}
