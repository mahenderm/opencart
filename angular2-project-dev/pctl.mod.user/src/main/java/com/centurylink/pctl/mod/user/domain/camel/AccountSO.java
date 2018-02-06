
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class AccountSO {

    @XStreamAlias("accountAliasName")
    protected String accountAliasName;
    protected String accountChannelId;
    protected String accountDisplayName;
    protected String accountId;
    protected String accountMarketUnitId;
    protected String accountName;
    protected String accountRegionCode;
    protected String accountSystemCode;
    protected String accountTypeCode;
    protected String bossBtnCc;
    protected String ctaId;

    @XStreamAlias("disconnectDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar disconnectDate;
    protected String discountGroupId;
    protected boolean marketingIndicator;
    protected String rjsAccountId;
    protected String salesRepId;
    protected String sourceSystem;
    protected String sourceSystemCode;
    protected String statusCode;
    protected String subAccountGroupId;
    protected boolean subAccountIndicator;
    protected String subAccountTypeCode;

    public String getSourceSystemCode() {
        return sourceSystemCode;
    }

    public void setSourceSystemCode(String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }

    public String getAccountAliasName() {
        return accountAliasName;
    }

    public void setAccountAliasName(String accountAliasName) {
        this.accountAliasName = accountAliasName;
    }

    public String getAccountChannelId() {
        return accountChannelId;
    }

    public void setAccountChannelId(String accountChannelId) {
        this.accountChannelId = accountChannelId;
    }

    public String getAccountDisplayName() {
        return accountDisplayName;
    }

    public void setAccountDisplayName(String accountDisplayName) {
        this.accountDisplayName = accountDisplayName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountMarketUnitId() {
        return accountMarketUnitId;
    }

    public void setAccountMarketUnitId(String accountMarketUnitId) {
        this.accountMarketUnitId = accountMarketUnitId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountRegionCode() {
        return accountRegionCode;
    }

    public void setAccountRegionCode(String accountRegionCode) {
        this.accountRegionCode = accountRegionCode;
    }

    public String getAccountSystemCode() {
        return accountSystemCode;
    }

    public void setAccountSystemCode(String accountSystemCode) {
        this.accountSystemCode = accountSystemCode;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getBossBtnCc() {
        return bossBtnCc;
    }

    public void setBossBtnCc(String bossBtnCc) {
        this.bossBtnCc = bossBtnCc;
    }

    public String getCtaId() {
        return ctaId;
    }

    public void setCtaId(String ctaId) {
        this.ctaId = ctaId;
    }

    public GregorianCalendar getDisconnectDate() {
        return disconnectDate;
    }

    public void setDisconnectDate(GregorianCalendar disconnectDate) {
        this.disconnectDate = disconnectDate;
    }

    public String getDiscountGroupId() {
        return discountGroupId;
    }

    public void setDiscountGroupId(String discountGroupId) {
        this.discountGroupId = discountGroupId;
    }

    public boolean isMarketingIndicator() {
        return marketingIndicator;
    }

    public void setMarketingIndicator(boolean marketingIndicator) {
        this.marketingIndicator = marketingIndicator;
    }

    public String getRjsAccountId() {
        return rjsAccountId;
    }

    public void setRjsAccountId(String rjsAccountId) {
        this.rjsAccountId = rjsAccountId;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getSubAccountGroupId() {
        return subAccountGroupId;
    }

    public void setSubAccountGroupId(String subAccountGroupId) {
        this.subAccountGroupId = subAccountGroupId;
    }

    public boolean isSubAccountIndicator() {
        return subAccountIndicator;
    }

    public void setSubAccountIndicator(boolean subAccountIndicator) {
        this.subAccountIndicator = subAccountIndicator;
    }

    public String getSubAccountTypeCode() {
        return subAccountTypeCode;
    }

    public void setSubAccountTypeCode(String subAccountTypeCode) {
        this.subAccountTypeCode = subAccountTypeCode;
    }

    @Override
    public String toString() {
        return "AccountSO{" +
                "accountAliasName='" + accountAliasName + '\'' +
                ", accountChannelId='" + accountChannelId + '\'' +
                ", accountDisplayName='" + accountDisplayName + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountMarketUnitId='" + accountMarketUnitId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountRegionCode='" + accountRegionCode + '\'' +
                ", accountSystemCode='" + accountSystemCode + '\'' +
                ", accountTypeCode='" + accountTypeCode + '\'' +
                ", bossBtnCc='" + bossBtnCc + '\'' +
                ", ctaId='" + ctaId + '\'' +
                ", disconnectDate=" + disconnectDate +
                ", discountGroupId='" + discountGroupId + '\'' +
                ", marketingIndicator=" + marketingIndicator +
                ", rjsAccountId='" + rjsAccountId + '\'' +
                ", salesRepId='" + salesRepId + '\'' +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", sourceSystemCode='" + sourceSystemCode + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", subAccountGroupId='" + subAccountGroupId + '\'' +
                ", subAccountIndicator=" + subAccountIndicator +
                ", subAccountTypeCode='" + subAccountTypeCode + '\'' +
                '}';
    }

    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy:dd:MM");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
}
