package com.gl.eshopping.exception;

public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(Long orderId) {
    }
}
