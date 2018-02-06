
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;


@XStreamAlias("getUserEnterprisesResponse")
public class GetUserEnterprisesResponse {

    @XStreamImplicit(itemFieldName = "return")
    protected List<EnterpriseSO> _return;


    public List<EnterpriseSO> getReturn() {
        if (_return == null) {
            _return = new ArrayList<EnterpriseSO>();
        }
        return this._return;
    }

    @Override
    public String toString() {
        return "GetUserEnterprisesResponse{" +
                "_return=" + _return +
                '}';
    }
}
