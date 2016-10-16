package classwork_jpa_hibernate.test;

import classwork_jpa_hibernate.dao.BookDao;
import classwork_jpa_hibernate.dao.Dao;
import classwork_jpa_hibernate.model.Book;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Created by Котято on 12.10.2016.
 */
public class TestDao {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate-unit");

        BookDao bookDao = new BookDao(factory);
        Book book = new Book("8", Book.BookType.IT, "kiev", new Date(), 300);
        Book book2 = new Book("2", Book.BookType.IT, "kiev", new Date(), 800);
        Book book3 = new Book("3", Book.BookType.PSYCHOLOGY, "kiev", new Date(), 200);
        Book book4 = new Book("4", Book.BookType.NOVEL, "kiev", new Date(), 600);

        bookDao.create(book);

        List<Book> booksIt = bookDao.getBookByType(Book.BookType.IT);
        booksIt.stream().forEach(System.out::println);
    }
}
