package com.itis.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CustomContextListener implements ServletContextListener {

    private static final String DB_URL= "jdbc:postgresql://localhost:5432/javalab_2021_pract";
    private static final String DB_DRIVER= "org.postgresql.Driver";
    private static final String DB_USER= "postgres";
    private static final String DB_PASSWORD= "qwerty007";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DB_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        HikariDataSource hikariDataSource = new HikariDataSource(config);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
