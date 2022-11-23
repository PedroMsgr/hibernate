package es.severo.persistence.test;

import es.severo.persistence.entity.Address;
import es.severo.persistence.entity.Address_;
import es.severo.persistence.entity.Person;
import es.severo.persistence.entity.Person_;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;


import java.util.List;

public class TestManyToMany {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            try {
                session.beginTransaction();

                Person p1 = new Person("Patri");
                Person p2 = new Person("Oscar");
                Person p3 = new Person("Lorenzo");

                Address a = new Address("Avenida Novelda","12");
                Address a1 = new Address("Camino los magros","12");
                Address a2 = new Address("Gines Garcia","14A");
                Address a3 = new Address("Rimba 2", "123E");

                p1.getAddresses().add(a);
                p1.getAddresses().add(a1);
                p2.getAddresses().add(a2);
                p3.getAddresses().add(a3);

                session.persist(p1);
                session.persist(p2);
                session.persist(p3);

                //Borrar una persona pero que no se borrar las direcciones
                Person p4 = session.find(Person.class,2L);
                session.remove(p4);

                //Consultar las direcciones de una persona dada

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
                Root<Address> root1 = criteria.from(Address.class);
                Join<Address,Person> joinAddres = root1.join(Address_.people);
                criteria.where(
                        builder.equal(joinAddres.get(Person_.id),1L)
                );
                List<Address> addressList = session.createQuery(criteria).getResultList();
                addressList.forEach(System.out::println);


                //Consultar las direcciones de una persana dada, que contenga plaza

                criteria
                        .select(root1)
                                .where(
                                        builder.and( builder.equal(joinAddres.get(Person_.id),1L)),
                                        builder.like(root1.get(Address_.street),"%Avenida%")
                                );

                addressList = session.createQuery(criteria).getResultList();
                addressList.forEach(System.out::println);

                Person pp = session.find(Person.class,3L);
                Address at = session.find(Address.class,2L);

                pp.getAddresses().remove(at);
                session.merge(pp);

                session.getTransaction().commit();
            }catch (RuntimeException ex){
                if (session.getTransaction()!= null){
                    session.getTransaction().rollback();
                }
                throw ex;
            }
        }
    }
}
