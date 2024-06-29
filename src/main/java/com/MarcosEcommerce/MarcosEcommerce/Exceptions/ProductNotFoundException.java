package com.MarcosEcommerce.MarcosEcommerce.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
