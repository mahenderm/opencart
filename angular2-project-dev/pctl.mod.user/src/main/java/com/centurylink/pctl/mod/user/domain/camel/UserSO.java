
package com.centurylink.pctl.mod.user.domain.camel;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class UserSO {

    @XStreamAlias("address1")
    protected String address1;
    @XStreamAlias("address2")
    protected String address2;
    @XStreamAlias("city")
    protected String city;
    protected String country;




    @XStreamAlias("createdDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar createdDate;


    protected String emailAddress;
    protected String emailAddressOnRecord;

    @XStreamAlias("emailModifiedDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar emailModifiedDate;
    protected String fax;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected String mobilePhone;
    protected boolean mobilePhoneUS;

    @XStreamAlias("modifiedDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar modifiedDate;
    protected String nightPhone;
    protected boolean nightPhoneUS;
    protected String notificationPref;
    protected String pager;
    protected boolean pagerUS;
    protected String phone;
    protected String phoneExt;
    protected boolean phoneUS;
    protected String realm;
    protected String state;
    protected boolean status;

    @XStreamAlias("timezone")
    protected Long timezone;
    protected Long userId;
    @XStreamAlias("userSetupDate")
    @XStreamConverter(com.thoughtworks.xstream.converters.extended.ISO8601GregorianCalendarConverter.class)
    protected GregorianCalendar userSetupDate;
    protected String userType;
    protected String username;
    protected String zip;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GregorianCalendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(GregorianCalendar createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddressOnRecord() {
        return emailAddressOnRecord;
    }

    public void setEmailAddressOnRecord(String emailAddressOnRecord) {
        this.emailAddressOnRecord = emailAddressOnRecord;
    }

    public GregorianCalendar getEmailModifiedDate() {
        return emailModifiedDate;
    }

    public void setEmailModifiedDate(GregorianCalendar emailModifiedDate) {
        this.emailModifiedDate = emailModifiedDate;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean isMobilePhoneUS() {
        return mobilePhoneUS;
    }

    public void setMobilePhoneUS(boolean mobilePhoneUS) {
        this.mobilePhoneUS = mobilePhoneUS;
    }

    public GregorianCalendar getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(GregorianCalendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getNightPhone() {
        return nightPhone;
    }

    public void setNightPhone(String nightPhone) {
        this.nightPhone = nightPhone;
    }

    public boolean isNightPhoneUS() {
        return nightPhoneUS;
    }

    public void setNightPhoneUS(boolean nightPhoneUS) {
        this.nightPhoneUS = nightPhoneUS;
    }

    public String getNotificationPref() {
        return notificationPref;
    }

    public void setNotificationPref(String notificationPref) {
        this.notificationPref = notificationPref;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public boolean isPagerUS() {
        return pagerUS;
    }

    public void setPagerUS(boolean pagerUS) {
        this.pagerUS = pagerUS;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt;
    }

    public boolean isPhoneUS() {
        return phoneUS;
    }

    public void setPhoneUS(boolean phoneUS) {
        this.phoneUS = phoneUS;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public GregorianCalendar getUserSetupDate() {
        return userSetupDate;
    }

    public void setUserSetupDate(GregorianCalendar userSetupDate) {
        this.userSetupDate = userSetupDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public static String format(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy:dd:MM");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }


    @Override
    public String toString() {
        return "UserSO{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", createdDate=" + format(createdDate) +
                ", emailAddress='" + emailAddress + '\'' +
                ", emailAddressOnRecord='" + emailAddressOnRecord + '\'' +
                ", emailModifiedDate=" + emailModifiedDate +
                ", fax='" + fax + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", mobilePhoneUS=" + mobilePhoneUS +
                ", modifiedDate=" + modifiedDate +
                ", nightPhone='" + nightPhone + '\'' +
                ", nightPhoneUS=" + nightPhoneUS +
                ", notificationPref='" + notificationPref + '\'' +
                ", pager='" + pager + '\'' +
                ", pagerUS=" + pagerUS +
                ", phone='" + phone + '\'' +
                ", phoneExt='" + phoneExt + '\'' +
                ", phoneUS=" + phoneUS +
                ", realm='" + realm + '\'' +
                ", state='" + state + '\'' +
                ", status=" + status +
                ", timezone=" + timezone +
                ", userId=" + userId +
                ", userSetupDate=" + userSetupDate +
                ", userType='" + userType + '\'' +
                ", username='" + username + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }



}



