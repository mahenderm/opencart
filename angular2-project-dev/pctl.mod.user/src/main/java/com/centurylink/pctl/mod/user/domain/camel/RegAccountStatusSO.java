
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class RegAccountStatusSO {

    @XStreamAlias("accountId")
    protected String accountId;
    @XStreamAlias("accountSystemCode")
    protected String accountSystemCode;
    @XStreamAlias("actionStatus")
    protected String actionStatus;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountSystemCode() {
        return accountSystemCode;
    }

    public void setAccountSystemCode(String accountSystemCode) {
        this.accountSystemCode = accountSystemCode;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    @Override
    public String toString() {
        return "RegAccountStatusSO{" +
            "accountId='" + accountId + '\'' +
            ", accountSystemCode='" + accountSystemCode + '\'' +
            ", actionStatus='" + actionStatus + '\'' +
            '}';
    }
}
