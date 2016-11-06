package classwork_jpa_hibernate.service;

import classwork_jpa_hibernate.dao.BookDao;
import classwork_jpa_hibernate.ioc.ForInject;
import classwork_jpa_hibernate.model.Book;
import classwork_jpa_hibernate.validator.Validator;

import java.util.List;

/**
 * Created by Котято on 02.11.2016.
 */

public class AuthorServiceImpl implements AuthorService {

    @ForInject
    private BookDao bookDao;
    @ForInject
    private Validator<Book> validator;

    public AuthorServiceImpl() {
    }

    public AuthorServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public String login(String name, String pass) {
        return null;
    }

    @Override
    public List<Book> getBooks(int authorId) {
        return null;
    }

    @Override
    public boolean addBook(Book book) {
       if(validator.isValid(book)){
           return false;
       }

        Book book1 = bookDao.create(book);
        return book1 != null;
    }
}
