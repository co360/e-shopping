package com.gl.eshopping.exception;

public class CustomerNotFoundException extends IllegalArgumentException{
    private Long customerId;
    public CustomerNotFoundException(Long customerId) {
        this.customerId = customerId;
    }
}
