package classwork.test;

import classwork.model.Address;
import classwork.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Котято on 13.10.2016.
 */
public class TestGetAddressFetchType {

    public static void main(String[] args) {

        EntityManagerFactory factory= Persistence.createEntityManagerFactory("hibernate-unit");

        EntityManager manager = factory.createEntityManager();

        Address address = manager.find(Address.class, 6);
        List<Author> authorList = address.getAuthorList();
        System.out.println(address);
        authorList.stream().forEach(System.out::println);

        manager.close();
        factory.close();
    }
}
