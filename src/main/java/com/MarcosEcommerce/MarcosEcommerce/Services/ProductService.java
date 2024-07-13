package com.MarcosEcommerce.MarcosEcommerce.Services;

import com.MarcosEcommerce.MarcosEcommerce.Builders.ProductBuilder;
import com.MarcosEcommerce.MarcosEcommerce.DTOs.RequestProductDto;
import com.MarcosEcommerce.MarcosEcommerce.DTOs.UpdateProductDto;
import com.MarcosEcommerce.MarcosEcommerce.Exceptions.EmptyDtoException;
import com.MarcosEcommerce.MarcosEcommerce.Exceptions.ProductNotFoundException;
import com.MarcosEcommerce.MarcosEcommerce.Models.Product;
import com.MarcosEcommerce.MarcosEcommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ICrudService<Product, Long> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
    @Override
    public Product save(Product product) {

        if (product.getName() == null || product.getName().isEmpty() || product.getName().isBlank() ) {
            throw new EmptyDtoException("Product name cannot be null or empty");
        }

        if (product.getDescription() == null || product.getDescription().isEmpty() || product.getDescription().isBlank() ) {
            throw new EmptyDtoException("Product description cannot be null or empty");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new EmptyDtoException("Product price must be greater than zero");
        }

        if (product.getQuantityInStock() == null || product.getQuantityInStock() < 0) {
            throw new EmptyDtoException("Product quantity in stock cannot be negative");
        }
        product.setName(product.getName().strip());
        product.setDescription(product.getDescription().strip());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product productMatch = optionalProduct.get();
            Product productBuilderValid = new ProductBuilder(productMatch)
                    .withName(product.getName())
                    .withDescription(product.getDescription())
                    .withPrice(product.getPrice())
                    .withStock(product.getQuantityInStock())
                    .build();

            return Optional.of(productRepository.save(productBuilderValid));
        }
        return  Optional.empty();
    }

    public void delete(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
