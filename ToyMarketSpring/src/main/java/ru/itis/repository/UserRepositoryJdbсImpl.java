package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.File;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryJdbсImpl implements UserRepository {

    private final static String SQL_SELECT_BY_EMAIL = "select * from users where email = :email;";
    private final static String SQL_SELECT_PASSWORD = "select * from users where email = :email;";
    private static final String SQL_INSERT_USERS = "insert into users(email, name, last_name, password, avatar) values (:email, :name, :lastName, :password, :avatar)";
    private static final String SQL_UPDATE_PHOTO = "update users set avatar= ? where email= ?;";
    private static final String SQL_UPDATE_PASSWORD = "update users set password= ? where email= ?;";

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getInt("id"))
            .email(row.getString("email"))
            .name(row.getString("name"))
            .password(row.getString("password"))
            .lastName(row.getString("last_name"))
            .avatar(row.getString("avatar"))
            .build();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbсImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,
                    Collections.singletonMap("email", email) , userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findPassByEmail(String email) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_PASSWORD,
                    Collections.singletonMap("email", email) , userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void saveUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> values = new HashMap<>();
        values.put("email", user.getEmail());
        values.put("name", user.getName());
        values.put("lastName", user.getLastName());
        values.put("password", user.getPassword());
        values.put("avatar", user.getAvatar());

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        namedParameterJdbcTemplate.update(SQL_INSERT_USERS, parameterSource, keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKeyAs(Integer.class));
    }

    @Override
    public void updatePass(String email, String password) {

        jdbcTemplate.update(SQL_UPDATE_PASSWORD, password, email);
    }

    @Override
    public void updatePhoto(String email, String photo) {
        jdbcTemplate.update(SQL_UPDATE_PHOTO, photo, email);
    }

}
