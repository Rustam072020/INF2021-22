package com.itis.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    Optional<T> findById(K id);

    T save(T item);

    void update(K id, T item);

    void delete(K id);

    List<T> findAll();

}
