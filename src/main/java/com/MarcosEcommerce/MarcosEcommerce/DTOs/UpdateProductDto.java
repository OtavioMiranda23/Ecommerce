package com.MarcosEcommerce.MarcosEcommerce.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductDto {
    private String name;
    private String description;
    private Integer price;
    private Integer quantityInStock;
}
