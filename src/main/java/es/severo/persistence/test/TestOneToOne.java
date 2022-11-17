package es.severo.persistence.test;

import es.severo.persistence.entity.Presupuesto;
import es.severo.persistence.entity.Tramite;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class TestOneToOne {
    public static void main(String[] args) {


        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

               /* Tramite t = new Tramite();
                t.setTipo("A");
                t.setFecha(LocalDateTime.now());
                session.persist(t);

                Presupuesto p = new Presupuesto();
                p.setLugarPresupuesto("Elche");
                p.setTramite(t);
                session.persist(p);

                session.flush();*/

                //Consultar trámite al que pertenece el presupuesto
                //Cargar el presupuesto
                Presupuesto pp = session.find(Presupuesto.class, 1L);
                pp.setLugarPresupuesto("Alicante");
                session.merge(pp);
                System.out.println(pp.getTramite());

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Presupuesto> criteria = builder.createQuery(Presupuesto.class);
                Root<Presupuesto> root = criteria.from(Presupuesto.class);
                criteria.select(root); //Select all

                List<Presupuesto> presupuestoList = session.createQuery(criteria)
                        .getResultList();
                presupuestoList.forEach(System.out::println);

                Presupuesto pdp = session.find(Presupuesto.class,2L);
                session.remove(pdp);
                session.getTransaction().commit();
            } catch (RuntimeException ex){
                if(session.getTransaction() != null){
                    session.getTransaction().rollback();
                    throw ex;
                }
            }
        }
    }
}

