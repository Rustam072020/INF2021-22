package ru.itis;

import Models.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    Optional<Account> findById(Integer id);
    List<Account> findAll();
    Account save(Account item);
    void update(Integer id, String item);
    void delete(Integer id);
}            
