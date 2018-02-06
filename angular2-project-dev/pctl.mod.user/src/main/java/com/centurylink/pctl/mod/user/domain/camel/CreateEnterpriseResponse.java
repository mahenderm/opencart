package com.centurylink.pctl.mod.user.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by senthilkumar-m on 02-11-2016.
 */
@XStreamAlias("createEnterpriseResponse")
public class CreateEnterpriseResponse {

   @XStreamAlias("return")
   public  String resultStr;

    @Override
    public String toString() {
        return "createEnterpriseResponse{" +
                "resultStr='" + resultStr + '\'' +
                '}';
    }
}
