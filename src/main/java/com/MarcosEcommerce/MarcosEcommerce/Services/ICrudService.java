package com.MarcosEcommerce.MarcosEcommerce.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICrudService<T, ID> {
    Page<T> getAll(Pageable page);
    T getById(ID id);
    Optional<T> update(ID id, T entity);
    T save(T entity);
    void delete(ID id);
}
