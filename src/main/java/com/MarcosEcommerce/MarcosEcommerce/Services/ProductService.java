package com.MarcosEcommerce.MarcosEcommerce.Services;

import com.MarcosEcommerce.MarcosEcommerce.DTOs.RequestProductDto;
import com.MarcosEcommerce.MarcosEcommerce.DTOs.UpdateProductDto;
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
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


    public Optional<Product> update(Long id, UpdateProductDto productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product productMatch = optionalProduct.get();

            if (productDTO.getName() != null) {
                productMatch.setName(productDTO.getName());
            }
            if (productDTO.getDescription() != null) {
                productMatch.setDescription(productDTO.getDescription());
            }
            if (productDTO.getPrice() != null) {
                productMatch.setPrice(productDTO.getPrice());
            }
            if (productDTO.getQuantityInStock() != null) {
                productMatch.setQuantityInStock(productDTO.getQuantityInStock());
            }
            return Optional.of(productRepository.save(productMatch));
        } else {
            return Optional.empty();
        }
    }

    public void delete(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
