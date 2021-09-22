package ru.itis;

import Models.Account;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.CrudRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CrudRepositoryJdbsImpl implements CrudRepository {

    JdbcOperations jdbcTemplate;

    public CrudRepositoryJdbsImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private String SQL_SELECT = "select * from account where id=?";
    private String SQL_SELECT_ALL = "select * from account";
    private String SQL_UPDATE = "update account set first_name = ? where id = ?";
    private String SQL_DELETE = "delete from account where id = ?";
    private String SQL_INSERT = "insert into account(first_name, last_name, age) values (?,?,?);";


    private final RowMapper<Account> accountRow = (row, i) -> {
        try {
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");
            int age = row.getInt("age");
            int id = row.getInt("id");
            Account account = new Account(id, firstName, lastName, age);
            return account;
        } catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public Optional<Account> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT, accountRow, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, accountRow);
    }

    @Override
    public Account save(Account item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastname());
            statement.setInt(3, item.getAge());
            return statement;
        }, keyHolder);
        item.setId((int) keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public void update(Integer id, String item) {
        jdbcTemplate.update(SQL_UPDATE, item, id);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
