
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class UserInfoSO {

    @Override
    public String toString() {
        return "UserInfoSO{" +
                "enterprise=" + enterprise +
                ", user=" + user +
                '}';
    }



    @XStreamAlias("enterprise")
    protected EnterpriseSO enterprise;



    @XStreamAlias("user")
    protected UserSO user;

    /**
     * Gets the value of the enterprise property.
     *
     * @return
     *     possible object is
     *     {@link EnterpriseSO }
     *
     */
    public EnterpriseSO getEnterprise() {
        return enterprise;
    }

    /**
     * Sets the value of the enterprise property.
     *
     * @param value
     *     allowed object is
     *     {@link EnterpriseSO }
     *
     */
    public void setEnterprise(EnterpriseSO value) {
        this.enterprise = value;
    }

    /**
     * Gets the value of the user property.
     *
     * @return
     *     possible object is
     *     {@link UserSO }
     *
     */
    public UserSO getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param value
     *     allowed object is
     *     {@link UserSO }
     *
     */
    public void setUser(UserSO value) {
        this.user = value;
    }

}
