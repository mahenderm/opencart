
package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;




@XStreamAlias("checkForUserResponse")
public class CheckForUserResponse {

    @XStreamImplicit(itemFieldName="return")
    //@XStreamAlias("return")
    protected List<UserInfoSO> _return;
//    protected UserInfoSO _return;

    @Override
    public String toString() {
        return "CheckForUserResponse{" +
                "_return=" + _return +
                '}';
    }

    public List<UserInfoSO> getReturn() {
        if (_return == null) {
            _return = new ArrayList<UserInfoSO>();
        }
        return this._return;
    }

}
