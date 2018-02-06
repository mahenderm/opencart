
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class EnterpriseSO {

    @XStreamAlias("createdDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar createdDate;
//    protected XMLGregorianCalendar createdDate;
//
    protected Boolean creditHoldIndicator;
    protected boolean deactivated;

    @XStreamAlias("deactivatedDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar deactivatedDate;
//    protected XMLGregorianCalendar deactivatedDate;
    protected String deactivatedStatusCode;
    protected Boolean defaultIndicator;
    protected Boolean desistAniIndicator;
    protected Boolean desistCardIndicator;
    protected Boolean desistDedicated8XXIndicator;
    protected Boolean desistSwitched8XXIndicator;
    protected Boolean enewsIndicator;
    protected String enterpriseChannelId;
    protected String enterpriseCustomerType;
    protected String enterpriseEmailAddress;

    @XStreamAlias("enterpriseId")
    protected Long enterpriseId;
    protected String enterpriseName;
    protected Boolean noPicIndicator;
    protected String originTypeCode;
    protected boolean parentEnterpriseInd;
    protected String remoteControlId;
    protected boolean selfRegistration;

    public GregorianCalendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(GregorianCalendar createdDate) {
        this.createdDate = createdDate;
    }

    public GregorianCalendar getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(GregorianCalendar deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }
    /* public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(XMLGregorianCalendar createdDate) {
        this.createdDate = createdDate;
    }

    public XMLGregorianCalendar getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(XMLGregorianCalendar deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }*/

    protected String userEnterpriseAlias;



    public Boolean getCreditHoldIndicator() {
        return creditHoldIndicator;
    }

    public void setCreditHoldIndicator(Boolean creditHoldIndicator) {
        this.creditHoldIndicator = creditHoldIndicator;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }



    public String getDeactivatedStatusCode() {
        return deactivatedStatusCode;
    }

    public void setDeactivatedStatusCode(String deactivatedStatusCode) {
        this.deactivatedStatusCode = deactivatedStatusCode;
    }

    public Boolean getDefaultIndicator() {
        return defaultIndicator;
    }

    public void setDefaultIndicator(Boolean defaultIndicator) {
        this.defaultIndicator = defaultIndicator;
    }

    public Boolean getDesistAniIndicator() {
        return desistAniIndicator;
    }

    public void setDesistAniIndicator(Boolean desistAniIndicator) {
        this.desistAniIndicator = desistAniIndicator;
    }

    public Boolean getDesistCardIndicator() {
        return desistCardIndicator;
    }

    public void setDesistCardIndicator(Boolean desistCardIndicator) {
        this.desistCardIndicator = desistCardIndicator;
    }

    public Boolean getDesistDedicated8XXIndicator() {
        return desistDedicated8XXIndicator;
    }

    public void setDesistDedicated8XXIndicator(Boolean desistDedicated8XXIndicator) {
        this.desistDedicated8XXIndicator = desistDedicated8XXIndicator;
    }

    public Boolean getDesistSwitched8XXIndicator() {
        return desistSwitched8XXIndicator;
    }

    public void setDesistSwitched8XXIndicator(Boolean desistSwitched8XXIndicator) {
        this.desistSwitched8XXIndicator = desistSwitched8XXIndicator;
    }

    public Boolean getEnewsIndicator() {
        return enewsIndicator;
    }

    public void setEnewsIndicator(Boolean enewsIndicator) {
        this.enewsIndicator = enewsIndicator;
    }

    public String getEnterpriseChannelId() {
        return enterpriseChannelId;
    }

    public void setEnterpriseChannelId(String enterpriseChannelId) {
        this.enterpriseChannelId = enterpriseChannelId;
    }

    public String getEnterpriseCustomerType() {
        return enterpriseCustomerType;
    }

    public void setEnterpriseCustomerType(String enterpriseCustomerType) {
        this.enterpriseCustomerType = enterpriseCustomerType;
    }

    public String getEnterpriseEmailAddress() {
        return enterpriseEmailAddress;
    }

    public void setEnterpriseEmailAddress(String enterpriseEmailAddress) {
        this.enterpriseEmailAddress = enterpriseEmailAddress;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Boolean getNoPicIndicator() {
        return noPicIndicator;
    }

    public void setNoPicIndicator(Boolean noPicIndicator) {
        this.noPicIndicator = noPicIndicator;
    }

    public String getOriginTypeCode() {
        return originTypeCode;
    }

    public void setOriginTypeCode(String originTypeCode) {
        this.originTypeCode = originTypeCode;
    }

    public boolean isParentEnterpriseInd() {
        return parentEnterpriseInd;
    }

    public void setParentEnterpriseInd(boolean parentEnterpriseInd) {
        this.parentEnterpriseInd = parentEnterpriseInd;
    }

    public String getRemoteControlId() {
        return remoteControlId;
    }

    public void setRemoteControlId(String remoteControlId) {
        this.remoteControlId = remoteControlId;
    }

    public boolean isSelfRegistration() {
        return selfRegistration;
    }

    public void setSelfRegistration(boolean selfRegistration) {
        this.selfRegistration = selfRegistration;
    }

    public String getUserEnterpriseAlias() {
        return userEnterpriseAlias;
    }

    public void setUserEnterpriseAlias(String userEnterpriseAlias) {
        this.userEnterpriseAlias = userEnterpriseAlias;
    }

    @Override
    public String toString() {
        return "EnterpriseSO{" +
                "createdDate=" + format(createdDate) +
                ", creditHoldIndicator=" + creditHoldIndicator +
                ", deactivated=" + deactivated +
                ", deactivatedDate=" + deactivatedDate +
                ", deactivatedStatusCode='" + deactivatedStatusCode + '\'' +
                ", defaultIndicator=" + defaultIndicator +
                ", desistAniIndicator=" + desistAniIndicator +
                ", desistCardIndicator=" + desistCardIndicator +
                ", desistDedicated8XXIndicator=" + desistDedicated8XXIndicator +
                ", desistSwitched8XXIndicator=" + desistSwitched8XXIndicator +
                ", enewsIndicator=" + enewsIndicator +
                ", enterpriseChannelId='" + enterpriseChannelId + '\'' +
                ", enterpriseCustomerType='" + enterpriseCustomerType + '\'' +
                ", enterpriseEmailAddress='" + enterpriseEmailAddress + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", noPicIndicator=" + noPicIndicator +
                ", originTypeCode='" + originTypeCode + '\'' +
                ", parentEnterpriseInd=" + parentEnterpriseInd +
                ", remoteControlId='" + remoteControlId + '\'' +
                ", selfRegistration=" + selfRegistration +
                ", userEnterpriseAlias='" + userEnterpriseAlias + '\'' +
                '}';
    }

    public static String format(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy:dd:MM");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
}
