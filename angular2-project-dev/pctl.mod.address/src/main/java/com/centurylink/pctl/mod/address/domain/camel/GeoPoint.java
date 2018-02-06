
package com.centurylink.pctl.mod.address.domain.camel;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class GeoPoint {

    @XStreamAlias("Source")
    protected GeoPointSource source;
    @XStreamAlias("Latitude")
    protected Double latitude;
    @XStreamAlias("Longitude")
    protected Double longitude;
    @XStreamAlias("CoordinateLevel")
    protected String coordinateLevel;
    @XStreamAlias("Accuracy")
    protected String accuracy;
    @XStreamAlias("Elevation")
    protected Double elevation;
    @XStreamAlias("Horizontal")
    protected Double horizontal;
    @XStreamAlias("Vertical")
    protected Double vertical;

    public GeoPoint() {
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getCoordinateLevel() {
        return coordinateLevel;
    }

    public void setCoordinateLevel(String coordinateLevel) {
        this.coordinateLevel = coordinateLevel;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Double horizontal) {
        this.horizontal = horizontal;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public GeoPointSource getSource() {
        return source;
    }

    public void setSource(GeoPointSource source) {
        this.source = source;
    }

    public Double getVertical() {
        return vertical;
    }

    public void setVertical(Double vertical) {
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
            "accuracy='" + accuracy + '\'' +
            ", source=" + source +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", coordinateLevel='" + coordinateLevel + '\'' +
            ", elevation=" + elevation +
            ", horizontal=" + horizontal +
            ", vertical=" + vertical +
            '}';
    }
}
