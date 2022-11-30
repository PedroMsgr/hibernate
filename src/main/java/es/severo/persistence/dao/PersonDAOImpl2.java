package es.severo.persistence.dao;

import es.severo.persistence.entity.Person;

import java.util.List;

public class PersonDAOImpl2 extends GenerricDAOImpl<Person> implements PersonDAO {

    public PersonDAOImpl2() {
        super(Person.class);
    }

    @Override
    public List<Person> getTotalAddres() {
        return null;
    }
}
