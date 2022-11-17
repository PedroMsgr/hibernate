package es.severo.persistence.test;

import es.severo.persistence.entity.Book;
import es.severo.persistence.entity.Chapter;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class TestOneToMany {
    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                Book book = new Book("123kekd","Fire","George Martin");
                Book book1 = new Book("afes345","Library","Michael Haid");

                Chapter chapter = new Chapter("Introduccion",123);
                chapter.setBook(book);
                Chapter chapter1 = new Chapter("Revenge",123);
                chapter1.setBook(book);
                Chapter chapter2 = new Chapter("Glossary",123);
                chapter2.setBook(book1);
                Chapter chapter3 = new Chapter("Summary",123);
                chapter3.setBook(book1);

                session.persist(book);
                session.persist(book1);
                session.persist(chapter);
                session.persist(chapter1);
                session.persist(chapter2);
                session.persist(chapter3);



                Book book2 = session.find(Book.class,2L);
                book2.getChapters().forEach(System.out::println);

                Chapter c = session.find(Chapter.class,13);
                System.out.print(c.getBook());


                //Libros
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
                Root<Book> root = criteriaQuery.from(Book.class);

                //Join<Book, Chapter> join = root.join(Book_.chapters);
                criteriaQuery.select(root);
                List<Book> books = session.createQuery(criteriaQuery).getResultList();
                books.forEach(System.out::println);

                //Consultar todos los capitulos de un libro dado

                CriteriaBuilder builder1 = session.getCriteriaBuilder();
                CriteriaQuery<Chapter> criteria = builder1.createQuery(Chapter.class);
                Root<Chapter> chapterRoot = criteria.from(Chapter.class);
                //Join<Chapter, Book> chapterBookJoin = chapterRoot.join(Chapter_.booK);
                criteria.
                        select(chapterRoot)
                                .where(
                                        //builder1.equal(chapterBookJoin,
                                                //session.find(Book.class,1L))
                                );
                List<Chapter> chapters = session.createQuery(criteria).getResultList();
                chapters.forEach(System.out::println);


                session.getTransaction().commit();
            } catch (RuntimeException ex){
                if (session.getTransaction()!= null){
                    session.getTransaction().rollback();
                }
                throw ex;
            }


        }
    }
}
