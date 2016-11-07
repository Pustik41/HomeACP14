package classwork.test;

import classwork.ioc.DependencyInjector;
import classwork.service.AuthorService;
import classwork.service.AuthorServiceImpl;

/**
 * Created by Котято on 02.11.2016.
 */
public class SisttInjector {

    public static void main(String[] args) {
        AuthorService authorService =  new AuthorServiceImpl();

        DependencyInjector.doInjection(authorService);
    }
}
