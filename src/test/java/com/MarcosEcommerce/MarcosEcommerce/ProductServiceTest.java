package com.MarcosEcommerce.MarcosEcommerce;

import com.MarcosEcommerce.MarcosEcommerce.Exceptions.EmptyDtoException;
import com.MarcosEcommerce.MarcosEcommerce.Models.Product;
import com.MarcosEcommerce.MarcosEcommerce.Repositories.ProductRepository;
import com.MarcosEcommerce.MarcosEcommerce.Services.ICrudService;
import com.MarcosEcommerce.MarcosEcommerce.Services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Test
    public void testSave() {
        Product product = new Product(
                "Electronic Bronze Sausages",
                "cunctatio tabesco dedecor",
                607,
                10);
        when(repository.save(product)).thenReturn(product);
        Product post = service.save(product);

        assertEquals(product, post);
    }

    @Test
    public void testSaveWithoutName() {
        Product product = new Product(
                null,
                "decimus amor sollers",
                497,
                1
        );
        assertThrowsExactly(EmptyDtoException.class, () -> {
            service.save(product);
        });
    }

    @Test
    public void testSaveProductWithEmptyName() {
        Product product = new Product(
                "", // Nome vazio
                "Description",
                100,
                10
        );

        assertThrows(EmptyDtoException.class, () -> {
            service.save(product);
        });
    }

    @Test
    public void testSaveProductWithWhitespaceName() {
        Product product = new Product(
                "   ", // Nome com espaços em branco
                "Description",
                100,
                10
        );

        assertThrows(EmptyDtoException.class, () -> {
            service.save(product);
        });
    }
    @Test
    @DisplayName("Test saving a product with negative price should throw IllegalArgumentException")
    public void testSaveWithNegativePrice() {
        Product product = new Product(
                "Product name",
                "Product description",
                -100,
                10
        );

        assertThrows(EmptyDtoException.class, () -> {
            service.save(product);
        });
    }

    @Test
    @DisplayName("Test saving a product with negative quantity should throw IllegalArgumentException")
    public void testSaveWithNegativeQuantity() {
        Product product = new Product(
                "Product name",
                "Product description",
                100,
                -10 // Quantidade negativa
        );

        assertThrows(EmptyDtoException.class, () -> {
            service.save(product);
        });
    }
    @Test
    @DisplayName("Test updating a product")
    public void testUpdate() {
        // Cria um produto existente
        Product existingProduct = new Product(
                1L,
                "SKU-56470aa6-739e-4369-9cdc-270bb7878d0e",
                "Electronic Bronze Sausages",
                "cunctatio tabesco dedecor",
                607,
                10);
        // Simula o comportamento do repository ao salvar o produto
        when(repository.save(existingProduct)).thenReturn(existingProduct);

        // Simula o serviço para atualizar o produto
        when(repository.findById(existingProduct.getId())).thenReturn(java.util.Optional.of(existingProduct));
        Optional<Product> updatedProduct = service.update(1L, existingProduct);

        // Verifica se o produto foi atualizado corretamente
        assertEquals(existingProduct, updatedProduct.get());
    }

    @Test
    @DisplayName("Test updating a product with empty name should throw EmptyDtoException")
    public void testUpdateWithEmptyName() {
        Product productToUpdate = new Product(
                1L,
                "SKU-56470aa6-739e-4369-9cdc-270bb7878d0e",
                "", // Nome vazio
                "Product description",
                100,
                10
        );

        // Simula o comportamento do repository ao salvar o produto
        when(repository.save(productToUpdate)).thenReturn(productToUpdate);

        // Simula o serviço para atualizar o produto
        when(repository.findById(productToUpdate.getId())).thenReturn(java.util.Optional.of(productToUpdate));

        // Verifica se lançará a exceção esperada ao tentar atualizar
        assertThrows(EmptyDtoException.class, () -> {
            service.update(1L, productToUpdate);
        });
    }

    @Test
    @DisplayName("Test updating a product with whitespace name should throw EmptyDtoException")
    public void testUpdateWithWhitespaceName() {
        Product productToUpdate = new Product(
                "   ", // Nome com espaços em branco
                "Product description",
                100,
                10
        );

        // Simula o comportamento do repository ao salvar o produto
        when(repository.save(productToUpdate)).thenReturn(productToUpdate);

        // Simula o serviço para atualizar o produto
        when(repository.findById(productToUpdate.getId())).thenReturn(java.util.Optional.of(productToUpdate));

        // Verifica se lançará a exceção esperada ao tentar atualizar
        assertThrows(EmptyDtoException.class, () -> {
            service.update(1L, productToUpdate);
        });
    }

    @Test
    @DisplayName("Test updating a product with negative price should throw IllegalArgumentException")
    public void testUpdateWithNegativePrice() {
        Product productToUpdate = new Product(
                "Product name",
                "Product description",
                -100,
                10
        );

        // Simula o comportamento do repository ao salvar o produto
        when(repository.save(productToUpdate)).thenReturn(productToUpdate);

        // Simula o serviço para atualizar o produto
        when(repository.findById(productToUpdate.getId())).thenReturn(java.util.Optional.of(productToUpdate));

        // Verifica se lançará a exceção esperada ao tentar atualizar
        assertThrows(EmptyDtoException.class, () -> {
            service.update(1L, productToUpdate);
        });
    }

    @Test
    @DisplayName("Test updating a product with negative quantity should throw IllegalArgumentException")
    public void testUpdateWithNegativeQuantity() {
        Product productToUpdate = new Product(
                "Product name",
                "Product description",
                100,
                -10 // Quantidade negativa
        );

        // Simula o comportamento do repository ao salvar o produto
        when(repository.save(productToUpdate)).thenReturn(productToUpdate);

        // Simula o serviço para atualizar o produto
        when(repository.findById(productToUpdate.getId())).thenReturn(java.util.Optional.of(productToUpdate));

        // Verifica se lançará a exceção esperada ao tentar atualizar
        assertThrows(IllegalArgumentException.class, () -> {
            service.update(1L, productToUpdate);
        });
    }

}
