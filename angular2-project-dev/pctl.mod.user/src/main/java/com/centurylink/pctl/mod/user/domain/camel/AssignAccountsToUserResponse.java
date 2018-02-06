
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;


@XStreamAlias("assignAccountsToUserResponse")
public class AssignAccountsToUserResponse {

    @XStreamImplicit(itemFieldName="return")
    protected List<RegAccountStatusSO> _return;


    public List<RegAccountStatusSO> getReturn() {
        if (_return == null) {
            _return = new ArrayList<RegAccountStatusSO>();
        }
        return this._return;
    }

    @Override
    public String toString() {
        return "AssignAccountsToUserResponse{" +
            "_return=" + _return +
            '}';
    }
}
