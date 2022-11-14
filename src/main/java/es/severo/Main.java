package es.severo;

import es.severo.persistence.entity.Tramite;
import es.severo.persistence.entity.Tramite_;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Tramite t = new Tramite();
            t.setFecha(LocalDateTime.now());
            t.setTipo("Credito");
            //HQL
            Query<Tramite> query = session.createQuery("from Tramite where tipo= :tipoTram", Tramite.class)
                    .setParameter("tipoTram","Credito");
            List<Tramite> tramites = query.getResultList();
            tramites.forEach(System.out::println);

            System.out.println("_________________________________");

            Query<Tramite> query2 = session.createQuery("from Tramite where tipo = ?1", Tramite.class)
                    .setParameter(1,"Credito");
            Tramite tramite = query2.setMaxResults(1).getSingleResult();
            System.out.println(tramite);

            System.out.println("_________________________________");

            try(Stream<Tramite> tramiteStream = session.createQuery("from Tramite where tipo = :tipoTram",
                    Tramite.class).setParameter("tipoTram", "Credito").getResultStream()) {
                List<Tramite> tt = tramiteStream.skip(2).limit(1).collect(Collectors.toList());
                tt.forEach(System.out::println);
            }
            System.out.println("_________________________________");
            //Scrollable
            try(ScrollableResults<Tramite> scrollableResults = session.createQuery("from Tramite where tipo = :tipoTram",
                    Tramite.class).setParameter("tipoTram", "Credito").scroll()) {
                while (scrollableResults.next()){
                    Tramite t2 = scrollableResults.get();
                    System.out.println(t2);
                }
            }
            try(Stream<Tramite> tramiteStream = session.createQuery("from Tramite where tipo = :tipoTram",
                    Tramite.class).setParameter("tipoTram", "Credito").getResultStream()) {
                Map<String,List<Tramite>> tt = tramiteStream.collect(Collectors.groupingBy(Tramite::getTipo));

            }
            System.out.println("________________CRITERIA________________");
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tramite> criteriaQuery = builder.createQuery(Tramite.class);
            Root<Tramite> root = criteriaQuery.from(Tramite.class);
            criteriaQuery.select(root).where(builder.equal(root.get(Tramite_.tipo),"Credito"));
            Tramite t1 = session.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e ){
            if (session != null && session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            throw e;
        }
        finally {
            if (session != null){
                session.close();
            }
            HibernateUtil.closeSessionFactory();
        }
    }
}
