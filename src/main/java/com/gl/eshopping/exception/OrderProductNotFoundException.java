package com.gl.eshopping.exception;

public class OrderProductNotFoundException extends NotFoundException {
    public OrderProductNotFoundException(Long orderProductId) {
        super("No Order Product with Id: " + orderProductId);
    }
}
