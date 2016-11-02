package classwork_jpa_hibernate.test;

import classwork_jpa_hibernate.ioc.DependencyInjector;
import classwork_jpa_hibernate.service.AuthorService;
import classwork_jpa_hibernate.service.AuthorServiceImpl;

/**
 * Created by Котято on 02.11.2016.
 */
public class SisttInjector {

    public static void main(String[] args) {
        AuthorService authorService =  new AuthorServiceImpl();

        DependencyInjector.doInjection(authorService);
    }
}
