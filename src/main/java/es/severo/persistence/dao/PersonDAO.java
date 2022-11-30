package es.severo.persistence.dao;

import com.mysql.cj.log.Log;
import es.severo.persistence.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO extends GenericDAO<Person>{

    List<Person> getTotalAddres();
}
