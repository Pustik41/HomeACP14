package classwork.dao;

import classwork.model.Book;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Котято on 12.10.2016.
 */
public class BookDao implements Dao<Book> {

    private static final Logger LOGGER = Logger.getLogger(BookDao.class);

    private EntityManagerFactory factory;

    public BookDao(EntityManagerFactory factory) {
        LOGGER.trace("create book dao instance");
        this.factory = factory;
    }

    @Override
    public Book create(Book book) {
        LOGGER.info("create new book");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(book);
            transaction.commit();
            LOGGER.info("book was saved");
        } catch (Exception e) {
            LOGGER.error("book was not saved", e);
            transaction.rollback();
        } finally {
            manager.close();
        }

        return book;
    }

    @Override
    public boolean delete(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        book = manager.find(Book.class, book.getId());

        try {
            transaction.begin();
            manager.remove(book);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            manager.close();
        }

        return true;
    }

    @Override
    public boolean update(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        Book originBook = manager.find(Book.class, book.getId());

        try {
            transaction.begin();
            originBook.setBookName(book.getBookName());
            manager.refresh(book);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            manager.close();
        }

        return true;
    }

    @Override
    public Book findById(Object id) {
        EntityManager manager = factory.createEntityManager();

        try {
            Book book = manager.find(Book.class, id);
            return book;
        } finally {
            manager.close();
        }
    }

    public List<Book> getBookByType(Book.BookType type){
        EntityManager manager = factory.createEntityManager();
        //JPQ - JAVA Persistence Query language  (OOP + SQL)
       TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.type=:typeName", Book.class);

        query.setParameter("typeName", type);
        query.setMaxResults(20);
        query.setFirstResult(0);

        return query.getResultList();
    }
}
