package ru.itis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.itis.dto.ProfileProductForm;
import ru.itis.models.Product;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_CATALOG_DECREASING = "select * from products order by cost";
    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_CATALOG_INCREASING = "select * from products order by cost desc ";
    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_CATALOG_NEW = "select * from products order by id desc";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from products where products.id = :id";
    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_SHOPPING_CART="select * from products where\n" +
            " id in (Select product_id from shopping_cart where user_email=:email);";
    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS_WISH_LIST="select * from products where\n" +
            " id in (Select product_id from wish_list where user_email=:email);";
    //language=SQL
    private static final String SQL_DELETE_PRODUCT_WISH_LIST = "delete from wish_list where product_id= :id and user_email=:email;";
    //language=SQL
    private static final String SQL_INSERT_TO_CART = "INSERT INTO shopping_cart(user_email, product_id) VALUES(:email, :id)";
    //language=SQL
    private static final String SQL_INSERT_TO_WISH_LIST = "INSERT INTO wish_list(user_email, product_id) VALUES(:email, :id)";
    //language=SQL
    private static final String SQL_DELETE_PRODUCT_CART = "delete from shopping_cart where product_id= :id and user_email=:email;";
    //language=SQL
    private static final String SQL_IS_NEW_PRODUCT_IN_CART =
            "SELECT product_id, user_email from shopping_cart where user_email = :email and product_id = :id;";
    //language=SQL
    private static final String SQL_IS_NEW_PRODUCT_IN_LIST =
            "SELECT product_id, user_email from wish_list where user_email = :email and product_id = :id;";
    //language=SQL
    private static final String SQL_SUM_COST = "select sum(cost) as email_cost from products where id in " +
            "(Select product_id from shopping_cart where user_email=:email)";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Integer> sumCostRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("email_cost");
        return id;
    };

    private final RowMapper<Product> productRowMapper = (row, rowNumber) -> Product.builder()
            .id(row.getInt("id"))
            .name(row.getString("product_name"))
            .picture(row.getString("picture"))
            .cost(row.getInt("cost"))
            .build();

    private final RowMapper<ProfileProductForm> productFormRowMapper = (row, rowNumber) -> ProfileProductForm.builder()
            .id(row.getInt("product_id"))
            .email(row.getString("user_email"))
            .build();


    private final SqlParameterSource createMap(ProfileProductForm form){
        Map<String, Object> values = new HashMap<>();
        values.put("id", form.getId());
        values.put("email", form.getEmail());
        SqlParameterSource parameterSource = new MapSqlParameterSource(values);
        return parameterSource;
    }

    @Override
    public List<Product> findAllInCatalogDecreasing() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_CATALOG_DECREASING, productRowMapper);
    }

    @Override
    public List<Product> findAllInCatalogIncreasing() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_CATALOG_INCREASING, productRowMapper);
    }

    @Override
    public List<Product> findAllInCatalogNew() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_CATALOG_NEW, productRowMapper);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id) , productRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAllInShoppingCart(String email) {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_SHOPPING_CART,
                Collections.singletonMap("email", email) , productRowMapper);
    }

    @Override
    public List<Product> findAllInWishList(String email) {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS_WISH_LIST,
                Collections.singletonMap("email", email) , productRowMapper);
    }

    @Override
    public void dropProductCart(ProfileProductForm form) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PRODUCT_CART, createMap(form));
    }

    @Override
    public void dropProductWishList(ProfileProductForm form) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PRODUCT_WISH_LIST, createMap(form));
    }

    @Override
    public void addToCart(ProfileProductForm form) {
        namedParameterJdbcTemplate.update(SQL_INSERT_TO_CART, createMap(form));
    }

    @Override
    public void addToList(ProfileProductForm form){
        namedParameterJdbcTemplate.update(SQL_INSERT_TO_WISH_LIST, createMap(form));
    }

    @Override
    public Optional<ProfileProductForm> productIsNewInCart(ProfileProductForm form) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_IS_NEW_PRODUCT_IN_CART,
                    createMap(form), productFormRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProfileProductForm> productIsNewInList(ProfileProductForm form) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_IS_NEW_PRODUCT_IN_LIST,
                    createMap(form), productFormRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> sumCostByEmail(String email){
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SUM_COST,
                    Collections.singletonMap("email", email) , sumCostRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }



}
