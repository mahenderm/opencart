package com.centurylink.pctl.mod.core.event;

import com.centurylink.pctl.mod.core.model.user.LoggedUser;

public interface Event {

	public void setHeader(EventHeader header);
	public void setUserInfo(LoggedUser eventInfo); // User Info
}
