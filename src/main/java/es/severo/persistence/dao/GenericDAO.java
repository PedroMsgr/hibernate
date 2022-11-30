package es.severo.persistence.dao;


import java.util.Optional;

public interface GenericDAO<T> {
    void create(T entity);

    void save(T entity);

    void deleteById(Long id);

    void delete(T entity);

    Optional<T> findById(Long id);
}
