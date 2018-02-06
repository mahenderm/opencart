package com.centurylink.pctl.mod.core.model.order;

import org.springframework.util.Assert;

/**
 * Created by veeraragavan.k on 07-11-2016.
 */
public enum OrderEventType {
    PURCHASED,
    CREATED,
    UPDATED,
    ORDERED,
    SHIPPED,
    DELIVERED;


    /**
     * getValueOf() method gives the Type of order event
     *
     * @param name , OrderEventType given as input
     * @return type, OrderEventType as  PURCHASED/CREATED/UPDATED/ORDERED/SHIPPED/DELIVERED returns
     */
    public static OrderEventType getValueOf(String name) {
        for (OrderEventType type : OrderEventType.values()) {
            Assert.notNull(name);
            if (type.name().equalsIgnoreCase(name))
                return type;
        }
        return null;
    }
}
