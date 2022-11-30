package es.severo.persistence.dao;

import es.severo.persistence.entity.Address;


import java.util.Optional;

public interface AddresDAO {
    void create(Address address);

    void save(Address address);

    void deleteById(Long idf);

    void delete(Address address);

    Optional<Address> findById(Long id);
}
