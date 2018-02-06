
package com.centurylink.pctl.mod.address.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class AddressManagementResponse {

    @XStreamAlias("Version")
    protected String version;
    @XStreamAlias("ServiceStatus")
    protected ServiceStatus serviceStatus;
    @XStreamAlias("TrackingId")
    protected String trackingId;
    @XStreamAlias("OverallDuration")
    protected double overallDuration;

    public AddressManagementResponse() {

    }

    public double getOverallDuration() {
        return overallDuration;
    }

    public void setOverallDuration(double overallDuration) {
        this.overallDuration = overallDuration;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AddressManagementResponse{" +
            "overallDuration=" + overallDuration +
            ", version='" + version + '\'' +
            ", serviceStatus=" + serviceStatus +
            ", trackingId='" + trackingId + '\'' +
            '}';
    }
}


