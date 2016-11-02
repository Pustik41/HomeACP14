package classwork_jpa_hibernate.ioc;

import classwork_jpa_hibernate.dao.BookDao;
import classwork_jpa_hibernate.service.AuthorService;
import classwork_jpa_hibernate.service.AuthorServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Котято on 02.11.2016.
 */
public class ServiceLocator {

    private static final Map<String, Object> context = new HashMap<>();

    //while class loading
    static {
        //configuretion
        context.put("entityManagerFactoty", Persistence.createEntityManagerFactory("hibernate-unit"));
        context.put("bookDao", new BookDao((EntityManagerFactory) context.get("entityManagerFactoty")));
        context.put("authorService", new AuthorServiceImpl((BookDao) context.get("bookDao")));

    }


    public static AuthorService get(String beanName) {
        return (AuthorService) context.get(beanName);
    }
}
