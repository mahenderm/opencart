package com.centurylink.pctl.mod.core.event;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String active;
	public UserInfo() {

	}


}
