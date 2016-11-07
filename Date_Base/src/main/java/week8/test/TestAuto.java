package week8.test;

import classwork.model.Book;
import classwork.service.AuthorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Котято on 07.11.2016.
 */
public class TestAuto {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ioc/app-xml-annot-context.xml");
        AuthorService authorService = applicationContext.getBean(AuthorService.class);
        List books = authorService.getBooks(-1);
        authorService.login("", "");


    }
}
