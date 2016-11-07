package classwork.test;

import classwork.ioc.ServiceLocator;
import classwork.dao.BookDao;
import classwork.service.AuthorService;
import classwork.service.AuthorServiceImpl;

import javax.persistence.Persistence;

/**
 * Created by Котято on 02.11.2016.
 */
public class TestAuthorService {

    public static void main(String[] args) {

        AuthorService authorService = new AuthorServiceImpl(new BookDao(Persistence.createEntityManagerFactory("hibernate-unit")));

        AuthorService authorService1 = ServiceLocator.get("authorService");
    }
}
