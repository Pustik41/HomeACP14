package classwork_jpa_hibernate.test;

import classwork_jpa_hibernate.model.Address;
import classwork_jpa_hibernate.model.Author;
import classwork_jpa_hibernate.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by Котято on 13.10.2016.
 */
public class TestRelations {

    public static void main(String[] args) {

        Address address = new Address("Kiev", "Iskrivska", "10");
        Author author = new Author("Alex", 123.84, new Date());
        address.setId(13);
        author.setAddress(address);

        EntityManagerFactory factory= Persistence.createEntityManagerFactory("hibernate-unit");

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        address = manager.find(Address.class, address.getId());
        try {
            transaction.begin();
            manager.remove(address);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }

        factory.close();
    }
}
