
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("getAccountDetailByIdResponse")
public class GetAccountDetailByIdResponse {

    @XStreamAlias("return")
    protected AccountDetailsVO _return;

    public AccountDetailsVO get_return() {
        return _return;
    }

    public void set_return(AccountDetailsVO _return) {
        this._return = _return;
    }

    @Override
    public String toString() {
        return "GetAccountDetailByIdResponse{" +
                "_return=" + _return +
                '}';
    }
}
