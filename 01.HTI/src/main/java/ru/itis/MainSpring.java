package ru.itis;

import Models.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainSpring {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CrudRepository crudRepository = context.getBean(CrudRepositoryJdbsImpl.class);
        System.out.println(crudRepository.findAll());
        crudRepository.update(1, "Рустам");
        crudRepository.delete(1);
        System.out.println(crudRepository.save(new Account("Олег","Олегов", 25)));
    }
}
