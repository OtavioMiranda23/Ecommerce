package com.MarcosEcommerce.MarcosEcommerce.Controllers;

import com.MarcosEcommerce.MarcosEcommerce.DTOs.RequestProductDto;
import com.MarcosEcommerce.MarcosEcommerce.Exceptions.ProductNotFoundException;
import com.MarcosEcommerce.MarcosEcommerce.Models.Product;
import com.MarcosEcommerce.MarcosEcommerce.Repositories.ProductRepository;
import com.MarcosEcommerce.MarcosEcommerce.Services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private  ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RequestProductDto>> getAllProducts() {
        List<Product> products = productService.getAll();
        List<RequestProductDto> productsDTOs = products.stream()
                .map(product -> modelMapper.map(product, RequestProductDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productsDTOs);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RequestProductDto> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if(product == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        RequestProductDto productDto = modelMapper.map(product, RequestProductDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody RequestProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getDescription(),
                productDto.getPrice(), productDto.getQuantityInStock());
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
