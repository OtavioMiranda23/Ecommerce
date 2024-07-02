package com.MarcosEcommerce.MarcosEcommerce.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestProductDto {
        @Valid

        @NotNull(message = "Name cannot be null")
        @NotBlank(message = "Name cannot be blank")
        private String name;

        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @NotBlank(message = "Name cannot be blank")
        private String description;

        @Min(value = 1, message = "The value must be greater than 0")
        private int price;

        @Min(value = 1, message = "The value must be greater than 0")
        private int quantityInStock;

        public RequestProductDto(String name, String description, int price, int quantityInStock) {
                this.name = name;
                this.description = description;
                this.price = price;
                this.quantityInStock = quantityInStock;
        }
}
