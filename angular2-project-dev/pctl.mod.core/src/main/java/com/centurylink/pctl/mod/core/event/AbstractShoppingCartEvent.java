package com.centurylink.pctl.mod.core.event;

import com.centurylink.pctl.mod.core.model.user.LoggedUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractShoppingCartEvent implements Event, Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(AbstractShoppingCartEvent.class);

    @JsonIgnore
    @Id
    protected String id;

    @JsonIgnore
    @Transient
    private  long processingStartTime;

    @JsonIgnore
    @Transient
    private long processingEndTime;

	protected LoggedUser userInfo = new LoggedUser();

    public AbstractShoppingCartEvent() {
        super();
        this.setProcessingStartTime(new Date().getTime());
    }
    public EventHeader header = new EventHeader();
    public EventHeader getHeader() {
        return header;
    }

    public void setHeader(EventHeader header) {
        this.header = header;
    }

	 public LoggedUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoggedUser userInfo) {
        this.userInfo = userInfo;
    }


	/*


    public AuditInfo get_audit() {
        return _audit;
    }

    public void set_audit(AuditInfo _audit) {
        this._audit = _audit;
    }

*/
    public long getProcessingStartTime() {
        return processingStartTime;
    }

    public void setProcessingStartTime(long processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    public long getProcessingEndTime() {
        return processingEndTime;
    }

    public void setProcessingEndTime(long processingEndTime) {
        this.processingEndTime = processingEndTime;
    }

}
