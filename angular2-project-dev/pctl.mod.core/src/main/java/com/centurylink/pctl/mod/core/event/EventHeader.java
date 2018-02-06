package com.centurylink.pctl.mod.core.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventHeader implements Serializable {


    private static final long serialVersionUID = 1L;

    public String eventId;
	public String name;

    public DateTime timestamp;

	public EventHeader() {

	}
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}


}
