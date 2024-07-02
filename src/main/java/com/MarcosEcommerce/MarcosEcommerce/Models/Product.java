package com.MarcosEcommerce.MarcosEcommerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String sku;

    private String name;
    private String description;
    private Integer price;
    private Integer quantityInStock;

    public Product(String name, String description, int price, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.sku = generateSKU();}

    private String generateSKU() {
        return "SKU-" + UUID.randomUUID().toString();
    }
}
