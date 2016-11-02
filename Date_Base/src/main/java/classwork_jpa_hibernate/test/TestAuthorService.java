package classwork_jpa_hibernate.test;

import classwork_jpa_hibernate.ioc.ServiceLocator;
import classwork_jpa_hibernate.dao.BookDao;
import classwork_jpa_hibernate.service.AuthorService;
import classwork_jpa_hibernate.service.AuthorServiceImpl;

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
