package classwork_jpa_hibernate.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InitTables {

    public static void main(String[] args) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("hibernate-unit");
    }
}
