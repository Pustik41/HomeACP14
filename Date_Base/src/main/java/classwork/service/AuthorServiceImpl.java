package classwork.service;

import classwork.dao.BookDao;
import classwork.ioc.ForInject;
import classwork.model.Book;
import classwork.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Котято on 02.11.2016.
 */
@Component
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
        return Arrays.asList(new Book(), new Book());
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
