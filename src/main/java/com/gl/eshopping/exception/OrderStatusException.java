package com.gl.eshopping.exception;

public class OrderStatusException extends IllegalArgumentException {
    public OrderStatusException(String message) {
        super(message);
    }
}
