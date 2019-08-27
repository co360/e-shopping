package com.gl.eshopping.dao;

import java.util.List;

public interface CrudDAO<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T save(T newObject);

    void delete(T object);

    void deleteById(ID id);
}
