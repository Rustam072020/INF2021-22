package ru.itis.education.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.education.model.Driver;
import ru.itis.education.repository.DriverRepositoryHibernate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        DriverRepositoryHibernate driverRepositoryHibernate = new DriverRepositoryHibernate(entityManager);

        for (int i = 1; i < 20; i++) {
            Driver driver = Driver.builder()
                    .firstName("name"+i)
                    .lastName("lastName"+i)
                    .age(i)
                    .build();
            //driverRepositoryHibernate.save(driver);
        }

        System.out.println(driverRepositoryHibernate.findById(9L));

        driverRepositoryHibernate.delete(9L);

        System.out.println(driverRepositoryHibernate.findAll());

    }
}
