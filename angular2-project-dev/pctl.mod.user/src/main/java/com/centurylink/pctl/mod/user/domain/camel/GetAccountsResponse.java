
package com.centurylink.pctl.mod.user.domain.camel;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("getAccountsResponse")
public class GetAccountsResponse {

    @XStreamAlias("return")
    protected AccountResponseSO _return;

    public AccountResponseSO get_return() {
        return _return;
    }

    public void set_return(AccountResponseSO _return) {
        this._return = _return;
    }

    @Override
    public String toString() {
        return "GetAccountsResponse{" +
                "_return=" + _return +
                '}';
    }
}
