package es.severo.persistence.test;

import es.severo.persistence.util.HibernateUtil;
import org.hibernate.Session;

public class TestManyToMany {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            try {

            }catch (RuntimeException ex){
                if (session.getTransaction()!= null){
                    session.getTransaction().rollback();
                }
                throw ex;
            }
        }
    }
}
