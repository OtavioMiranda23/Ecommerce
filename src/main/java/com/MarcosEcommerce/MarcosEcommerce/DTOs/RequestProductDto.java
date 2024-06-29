package com.MarcosEcommerce.MarcosEcommerce.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProductDto {
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @NotBlank(message = "Name cannot be blank")
        private String name;

        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @NotBlank(message = "Name cannot be blank")
        private String description;

        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @NotBlank(message = "Name cannot be blank")
        private int price;

        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @NotBlank(message = "Name cannot be blank")
        private int quantityInStock;

        public RequestProductDto(String name, String description, int price, int quantityInStock) {
                this.name = name;
                this.description = description;
                this.price = price;
                this.quantityInStock = quantityInStock;
        }
}
