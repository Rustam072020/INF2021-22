package ru.itis.education.repository;

import ru.itis.education.model.Driver;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class DriverRepositoryHibernate implements CrudRepository<Driver, Long> {

    private final EntityManager entityManager;

    public DriverRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Driver> findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<Driver> driver = Optional.of(entityManager.find(Driver.class, id));
        transaction.commit();
        return driver;
    }

    @Override
    public List<Driver> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Driver> drivers = entityManager.createQuery("select driver from Driver driver", Driver.class).getResultList();
        transaction.commit();
        return drivers;
    }

    @Override
    public Driver save(Driver item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return null;
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Driver driver = entityManager.find(Driver.class, id);
        entityManager.remove(driver);
        transaction.commit();
    }
}