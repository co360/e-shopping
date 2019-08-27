package com.gl.eshopping.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    APPROVED("approved"),
    CANCELLED("cancelled"),
    CANCELLED_BY_USER("user cancelled"),
    CANCELLED_BY_Shop("shop cancelled"),
    PREPARING("preparing"),
    PICKED_UP("picked up"),
    DELIVERED("delivered");

    String description;
    private static final Map<String, OrderStatus> LOOKUP;

    static  {
        LOOKUP = new HashMap<>();
        for(OrderStatus orderStatus: EnumSet.allOf(OrderStatus.class)) {
            LOOKUP.put(orderStatus.getDescription(), orderStatus);
        }
    }

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    @JsonCreator
    public static OrderStatus fromDescription(String description){
        if(description == null) {
            throw new IllegalArgumentException("Order Status Description cannot be null");
        }
        if(LOOKUP.containsKey(description)) {
            return LOOKUP.get(description);
        }
        throw new IllegalArgumentException("No Order Status found with given Description.");
    }

    @Override
    public String toString() {
        return description;
    }
}
