package com.MarcosEcommerce.MarcosEcommerce.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    @ManyToMany()
    @JoinTable(name = "product_category",
                joinColumns = @JoinColumn(name = "category_fk"),
                inverseJoinColumns = @JoinColumn(name = "product_fk")
    )
    private List<Product> products;
}
