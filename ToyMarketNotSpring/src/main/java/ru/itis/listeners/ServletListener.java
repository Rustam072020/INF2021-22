package ru.itis.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.repository.*;
import ru.itis.services.products.*;
import ru.itis.services.user.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;


@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/application.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver"));
        hikariConfig.setUsername(properties.getProperty("db.user"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        FilesRepository filesRepository = new FilesRepositoryJdbсImpl(dataSource);
        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
        UserRepository userRepository = new UserRepositoryJdbсImpl(dataSource);

        CatalogService catalogService = new CatalogServiceImpl(productsRepository);
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(productsRepository);
        WishListService wishListService = new WishListServiceImpl(productsRepository);
        ProfileService profileService = new ProfileServiceImpl(userRepository);
        SettingsService settingsService = new SettingsServiceImpl(filesRepository, userRepository);
        SignInService signInService = new SignInServiceImpl(userRepository);
        SignUpService signUpService = new SignUpServiceImpl(userRepository);


        servletContext.setAttribute("catalogService", catalogService);
        servletContext.setAttribute("shoppingCartService", shoppingCartService);
        servletContext.setAttribute("wishListService", wishListService);
        servletContext.setAttribute("profileService", profileService);
        servletContext.setAttribute("settingsService", settingsService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
