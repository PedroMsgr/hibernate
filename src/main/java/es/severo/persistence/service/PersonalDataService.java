package es.severo.persistence.service;

import es.severo.persistence.dao.GenericDAO;
import es.severo.persistence.dao.GenerricDAOImpl;
import es.severo.persistence.dao.PersonDAO;
import es.severo.persistence.entity.Address;
import es.severo.persistence.entity.Person;

import java.util.List;

public class PersonalDataService {
    private final GenericDAO<Person> genericDAO;

    public PersonalDataService() {
        this.genericDAO = new GenerricDAOImpl<>(Person.class);
    }

    public void createPerson(Person person, List<Address> addresses){
        if (addresses != null){
            for (Address a :
                 addresses) {
                person.getAddresses().add(a);
            }
        }
        genericDAO.create(person);
    }
}
