package com.centurylink.pctl.mod.core.model.user;


import com.centurylink.pctl.mod.core.model.address.Address;
import com.centurylink.pctl.mod.core.model.order.BillingContactDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoggedUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("authToken")
    private String token;

    private String id;

    @Field("loggedIn")
    private Boolean loggedIn;

    @Field("status")
    private String status;

    @Field("login")
    private String login;

    @JsonIgnore
    @Field("password")
    private String password;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email")
    private String email;

    @Field("phoneNumber")
    private String phoneNumber;

    private String activated;

    @Field("finger_print")
    private String fingerPrint;

    @Field("primaryPhoneNumber")
    private String primaryPhoneNumber;

    @Field("companyName")
    private String companyName;

    @Field("billingAddress")
    private Set<Address> billingAddress = new HashSet<Address>();

    @Field("einNumber")
    private String einNumber;

    @Field("authorities")
    private Set<LoggedUserAuthority> authorities = new HashSet<LoggedUserAuthority>();

    @Field("cartHist")
    private Set<CartInfo> cartHist = new HashSet<CartInfo>();
    @Field("cartInfo")
    private  CartInfo cartInfo;

    @Field("orderHist")
    private Set<OrderInfo> orderHist = new HashSet<OrderInfo>();
    @Field("orderInfo")
    private  OrderInfo orderInfo;

    @Field("billingContactDetails")
    private BillingContactDetails billingContactDetails;

    public LoggedUser() {

    }
    @JsonCreator
  public LoggedUser(@JsonProperty("id")String id,
                    @JsonProperty("login")String login,
                    @JsonProperty("password")String password,
                    @JsonProperty("firstName")String firstName,
                    @JsonProperty("lastName")String lastName,
                    @JsonProperty("email")String email,
                    @JsonProperty("phoneNumber")String phoneNumber,
                    @JsonProperty("companyName")String companyName,
                    @JsonProperty("einNumber")String einNumber,
                    @JsonProperty("activated") String activated,
                    @JsonProperty("authorities") Set<LoggedUserAuthority> authorities,
                    @JsonProperty("billingAddress")Set<Address> billingAddress,
                    @JsonProperty("billingContactDetails")BillingContactDetails billingContactDetails,
                    @JsonProperty("cartHist")Set<CartInfo> cartHist,
                    @JsonProperty("cartInfo")CartInfo cartInfo,
                    @JsonProperty("orderHist")Set<OrderInfo> orderHist,
                    @JsonProperty("orderInfo")OrderInfo orderInfo){
    this.id = id;
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.activated = activated;
    this.authorities = authorities;
    this.cartHist = cartHist;
    this.cartInfo=  cartInfo;
    this.orderHist =  orderHist;
    this.orderInfo=  orderInfo;
    this.phoneNumber = phoneNumber;
    this.companyName = companyName;
    this.einNumber=einNumber;
    this.billingAddress= billingAddress;
    this.billingContactDetails = billingContactDetails;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Set<LoggedUserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<LoggedUserAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<CartInfo> getCartHist() {
        return cartHist;
    }

    public void setCartHist(Set<CartInfo> cartHist) {
        this.cartHist = cartHist;
    }

    public CartInfo getCartInfo() {
        return cartInfo;
    }

    public void setCartInfo(CartInfo cartInfo) {
        this.cartInfo = cartInfo;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Set<OrderInfo> getOrderHist() {
        return orderHist;
    }

    public void setOrderHist(Set<OrderInfo> orderHist) {
        this.orderHist = orderHist;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getEinNumber() {
        return einNumber;
    }

    public void setEinNumber(String einNumber) {
        this.einNumber = einNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Address> getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Set<Address> billingAddress) {
        this.billingAddress = billingAddress;
    }

    public BillingContactDetails getBillingContactDetails() {
        return billingContactDetails;
    }

    public void setBillingContactDetails(BillingContactDetails billingContactDetails) {
        this.billingContactDetails = billingContactDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoggedUser)) return false;

        LoggedUser that = (LoggedUser) o;

        if (activated != null ? !activated.equals(that.activated) : that.activated != null) return false;
        if (authorities != null ? !authorities.equals(that.authorities) : that.authorities != null) return false;
        if (billingAddress != null ? !billingAddress.equals(that.billingAddress) : that.billingAddress != null)
            return false;
        if (billingContactDetails != null ? !billingContactDetails.equals(that.billingContactDetails) : that.billingContactDetails != null)
            return false;
        if (cartHist != null ? !cartHist.equals(that.cartHist) : that.cartHist != null) return false;
        if (cartInfo != null ? !cartInfo.equals(that.cartInfo) : that.cartInfo != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (einNumber != null ? !einNumber.equals(that.einNumber) : that.einNumber != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (fingerPrint != null ? !fingerPrint.equals(that.fingerPrint) : that.fingerPrint != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (loggedIn != null ? !loggedIn.equals(that.loggedIn) : that.loggedIn != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (orderHist != null ? !orderHist.equals(that.orderHist) : that.orderHist != null) return false;
        if (orderInfo != null ? !orderInfo.equals(that.orderInfo) : that.orderInfo != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (primaryPhoneNumber != null ? !primaryPhoneNumber.equals(that.primaryPhoneNumber) : that.primaryPhoneNumber != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (loggedIn != null ? loggedIn.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (activated != null ? activated.hashCode() : 0);
        result = 31 * result + (fingerPrint != null ? fingerPrint.hashCode() : 0);
        result = 31 * result + (primaryPhoneNumber != null ? primaryPhoneNumber.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (einNumber != null ? einNumber.hashCode() : 0);
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        result = 31 * result + (cartHist != null ? cartHist.hashCode() : 0);
        result = 31 * result + (cartInfo != null ? cartInfo.hashCode() : 0);
        result = 31 * result + (orderHist != null ? orderHist.hashCode() : 0);
        result = 31 * result + (orderInfo != null ? orderInfo.hashCode() : 0);
        result = 31 * result + (billingContactDetails != null ? billingContactDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
            "token='" + token + '\'' +
            ", id='" + id + '\'' +
            ", loggedIn=" + loggedIn +
            ", status='" + status + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", activated='" + activated + '\'' +
            ", fingerPrint='" + fingerPrint + '\'' +
            ", primaryPhoneNumber='" + primaryPhoneNumber + '\'' +
            ", companyName='" + companyName + '\'' +
            ", billingAddress=" + billingAddress +
            ", einNumber='" + einNumber + '\'' +
            ", authorities=" + authorities +
            ", cartHist=" + cartHist +
            ", cartInfo=" + cartInfo +
            ", orderHist=" + orderHist +
            ", orderInfo=" + orderInfo +
            ", billingContactDetails=" + billingContactDetails +
            '}';
    }
}
