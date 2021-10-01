package com.itis.repository;

import com.itis.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CrudRepositoryJdbsImpl implements CrudRepository<User, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final static String SQL_SELECT_ALL = "select * from users;";
    private final static String SQL_INSERT = "insert into users (name, password) VALUES (?, ?);";
    private final static String SQL_SELECT_BY_ID = "select * from users where id = ?;";
    private final static String SQL_UPDATE_BY_ID = "update users set name = ?, password = ? where id = ?;";
    private final static String SQL_DELETE_BY_ID = "delete from users where id = ?;";
    private final static String SQL_SELECT_BY_NAME = "select * from users where name = ?;";
    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .password(row.getString("password"))
            .build();

    public CrudRepositoryJdbsImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    public Optional<User> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, userRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public User save(User item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, item.getName());
            statement.setString(2, item.getPassword());
            return statement;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public void update(Long id, User item) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, item.getName(), item.getPassword(), id);

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
