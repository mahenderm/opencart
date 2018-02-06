
package com.centurylink.pctl.mod.address.domain.camel;

import java.util.ArrayList;
import java.util.List;


public class ArrayOfGeoPoint {


    protected List<GeoPoint> geoPoint;

    public ArrayOfGeoPoint() {
    }

    public List<GeoPoint> getGeoPoint() {
        if (geoPoint == null) {
            geoPoint = new ArrayList<GeoPoint>();
        }
        return this.geoPoint;
    }

    public void setGeoPoint(List<GeoPoint> geoPoint) {
        this.geoPoint = geoPoint;
    }

    @Override
    public String toString() {
        return "ArrayOfGeoPoint{" +
            "geoPoint=" + geoPoint +
            '}';
    }
}
