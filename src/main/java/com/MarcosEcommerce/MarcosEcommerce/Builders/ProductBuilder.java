package com.MarcosEcommerce.MarcosEcommerce.Builders;

import com.MarcosEcommerce.MarcosEcommerce.Exceptions.EmptyDtoException;
import com.MarcosEcommerce.MarcosEcommerce.Exceptions.ProductNotFoundException;
import com.MarcosEcommerce.MarcosEcommerce.Models.Product;

public class ProductBuilder {
    private Product product;
    public ProductBuilder(Product product) {
        this.product = product;
    }

    public ProductBuilder withName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            product.setName(name);
        }
        return this;
    }

    public ProductBuilder withDescription(String name) {
        if (name != null && !name.trim().isEmpty()) {
            product.setDescription(name);
        }
        return this;
    }
    public  ProductBuilder withPrice(Integer price) {
        if (price != null && price > 0) {
            product.setPrice(price);
        }
        return this;
    }
    public  ProductBuilder withStock(Integer quantity) {
        if (quantity != null && quantity >= 0) {
            product.setQuantityInStock(quantity);
        }
        return this;
    }
    public Product build() {
        System.out.println(">" + product.getName() + product.getName().trim().isEmpty());
        boolean isValidProduct = (product.getName() != null && !product.getName().trim().isEmpty())
                && (product.getDescription() != null && !product.getDescription().trim().isEmpty())
                && (product.getPrice() != null && product.getPrice() > 0)
                && (product.getQuantityInStock() != null && product.getQuantityInStock() >= 0);
        if (isValidProduct) {
            return product;
        }
        throw new EmptyDtoException("All fields are invalid");


    }

}
