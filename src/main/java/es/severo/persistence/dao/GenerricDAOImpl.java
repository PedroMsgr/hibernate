package es.severo.persistence.dao;

import es.severo.persistence.entity.Person;
import es.severo.persistence.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class GenerricDAOImpl<T> implements GenericDAO<T> {

    private final Class<T> entityClass;

    public GenerricDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(entity);
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
    public void save(T entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.merge(entity);
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
                session.remove(session.find(entityClass,id));
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
    public void delete(T entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(entity);
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
    public Optional<T> findById(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(entityClass,id));
        }
    }
}
