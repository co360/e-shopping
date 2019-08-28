package com.gl.eshopping.exception;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long productId) {
        super("Product with Id: " + productId);
    }
}
