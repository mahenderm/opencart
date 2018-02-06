package com.centurylink.pctl.mod.core.model.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * CartEventType Enum
 *
 * @author nagavenkatakirang
 */
public enum CartEventType {
    ADD_ITEM,
    REMOVE_ITEM,
    ADD_LOCATION,
    UPDATE_LOCATION,
    REMOVE_LOCATION,
    CLEAR_CART,
    CHECKOUT;
    /**
     * List created to add CartEventType available for cartEvent
     */
    private final static List<String> cartEventTypes = new ArrayList<>();

    /**
     * static block adding available cartEventType in a list
     */
    static {
        for (CartEventType type : CartEventType.values()) {
            cartEventTypes.add(type.name());
        }
    }

    /**
     * getValueOf() method gives the CartEventType of cart
     *
     * @param name , CartEventType given as input
     * @return type, CartEventType as  ADD_ITEM or ADD_LOCATION returns
     */
    public static CartEventType getValueOf(String name) {
        for (CartEventType type : CartEventType.values()) {
            if (type.name().equalsIgnoreCase(name))
                return type;
        }
        return null;
    }
    /**
     * validate() method gives the CartEventType of cart
     *
     * @param name , CartEventType given as input
     * @return type, CartEventType as  ADD_ITEM or ADD_LOCATION returns
     */
    public static CartEventType validate(CartEventType name) {
        for (CartEventType type : CartEventType.values()) {
            if (type==name)
                return type;
        }
        return null;
    }
}
