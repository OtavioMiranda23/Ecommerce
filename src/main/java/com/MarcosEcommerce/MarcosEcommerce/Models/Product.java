package com.MarcosEcommerce.MarcosEcommerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

    public Product(String name, String description, int price, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.sku = generateSKU();
        this.createdAt = LocalDateTime.now();
    }

    private String generateSKU() {
        return "SKU-" + UUID.randomUUID().toString();
    }
}
