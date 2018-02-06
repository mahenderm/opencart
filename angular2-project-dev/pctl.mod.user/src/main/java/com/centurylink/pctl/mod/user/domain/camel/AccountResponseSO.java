
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;


public class AccountResponseSO {

    @XStreamImplicit(itemFieldName ="accounts" )
    protected List<AccountSO> accounts;
    @XStreamAlias("rowCount")
    protected int rowCount;
    @XStreamAlias("totalRowCount")
    protected int totalRowCount;


    public List<AccountSO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountSO> accounts) {
        this.accounts = accounts;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    @Override
    public String toString() {
        return "AccountResponseSO{" +
                "accounts=" + accounts +
                ", rowCount=" + rowCount +
                ", totalRowCount=" + totalRowCount +
                '}';
    }
}
