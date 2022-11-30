package es.severo.persistence.dao;

import es.severo.persistence.entity.Person;
import es.severo.persistence.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO{
    @Override
    public void create(Person person) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(person);
                tx.commit();
            }catch (RuntimeException e){
                if (tx != null){
                    tx.rollback();
                }
                e.printStackTrace();
            }

        }
    }

    @Override
    public void save(Person person) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.merge(person);
                tx.commit();
            }catch (RuntimeException e){
                if (tx != null){
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(session.find(Person.class,id));
                tx.commit();
            }catch (RuntimeException e){
                if (tx != null){
                    tx.rollback();
                }
                e.printStackTrace();
            }

        }

    }

    @Override
    public void delete(Person person) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(person);
                tx.commit();
            }catch (RuntimeException e){
                if (tx != null){
                    tx.rollback();
                }
                e.printStackTrace();
            }

        }
    }

    @Override
    public Optional<Person> findById(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(Person.class,id));
        }
    }

    @Override
    public List<Person> getTotalAddres() {
        return null;
    }
}
