package es.severo.persistence.test;

import es.severo.persistence.dao.GenericDAO;
import es.severo.persistence.dao.GenerricDAOImpl;
import es.severo.persistence.entity.Person;

public class TestManyToMany2 {
    public static void main(String[] args) {
        GenericDAO<Person> genericDAO =
                new GenerricDAOImpl<>(Person.class);

        genericDAO.create(new Person("Maria"));
    }
}
