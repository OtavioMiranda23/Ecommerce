package com.MarcosEcommerce.MarcosEcommerce.Services;

import com.MarcosEcommerce.MarcosEcommerce.DTOs.RequestProductDto;
import com.MarcosEcommerce.MarcosEcommerce.Exceptions.ProductNotFoundException;
import com.MarcosEcommerce.MarcosEcommerce.Models.Product;
import com.MarcosEcommerce.MarcosEcommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


//    public Optional<Product> update(Long id, RequestProductDto productDTO) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isPresent()) {
//            Product productMatch = optionalProduct.get();
//
//            if (productDTO.name() != null) {
//                productMatch.setName(productDTO.name());
//            }
//            if (productDTO.description() != null) {
//                productMatch.setDescription(productDTO.description());
//            }
//            if (productDTO.price() != 0) {
//                productMatch.setPrice(productDTO.price());
//            }
//            if (productDTO.quantityInStock() != 0) {
//                productMatch.setQuantityInStock(productDTO.quantityInStock());
//            }
//            return Optional.of(productRepository.save(productMatch));
//        } else {
//            return Optional.empty();
//        }
//    }

    public void delete(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
