package com.miletodev.api_bancario.service;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CrudService<ID, T> {
    List<T> findAll();
    T findById(ID id) throws ChangeSetPersister.NotFoundException;
    T create(T entity);
    T update(ID id, T entity) throws ChangeSetPersister.NotFoundException;
    void delete(ID id) throws ChangeSetPersister.NotFoundException;
}
