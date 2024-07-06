package com.MarcosEcommerce.MarcosEcommerce.Controllers;

import com.MarcosEcommerce.MarcosEcommerce.DTOs.RequestProductDto;
import com.MarcosEcommerce.MarcosEcommerce.DTOs.UpdateProductDto;
import com.MarcosEcommerce.MarcosEcommerce.Exceptions.ProductNotFoundException;
import com.MarcosEcommerce.MarcosEcommerce.Models.Product;
import com.MarcosEcommerce.MarcosEcommerce.Repositories.ProductRepository;
import com.MarcosEcommerce.MarcosEcommerce.Services.ProductService;
import com.MarcosEcommerce.MarcosEcommerce.Utils.DtoUtils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<List<RequestProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Product> products = productService.getAll(pageable);
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
    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductDto body) {
        //Lança exceção se o body estiver vazio;
        DtoUtils.isEmpty(body);
        Product product = modelMapper.map(body, Product.class);

        Optional<Product> productMatch = productService.update(id, product);
        if (productMatch.isEmpty()) {
            throw new ProductNotFoundException("Product not founded");
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
