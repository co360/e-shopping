package com.gl.eshopping.exception;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long categoryId) {
        super("No Category with Id: " + categoryId);
    }
}
