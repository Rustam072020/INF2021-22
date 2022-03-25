package ru.itis.servlets.product;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;
import ru.itis.models.Product;
import ru.itis.services.products.CatalogService;
import ru.itis.services.products.CatalogServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@PropertySource("classpath:application.properties")
@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    private CatalogService catalogService;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.catalogService = applicationContext.getBean(CatalogService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductsForm> products =  catalogService.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String email = (String) request.getSession().getAttribute("email");
        int id = 0;
        List<ProductsForm> products;
        ProfileProductForm form;
        if (email == null) {
            response.sendError(403);
            response.sendRedirect("/signIn");
        } else {
            if (action != null) {
                switch (action) {
                    case "addToWishList":
                        id = Integer.parseInt(request.getParameter("id"));
                        form = ProfileProductForm.builder()
                                .id(id)
                                .email(email)
                                .build();
                        if (!catalogService.isNewProductInList(form)) {
                            catalogService.addProductToList(form);
                        }
                        break;
                    case "addToCart":
                        id = Integer.parseInt(request.getParameter("id"));
                        form = ProfileProductForm.builder()
                                .id(id)
                                .email(email)
                                .build();
                        if (!catalogService.isNewProductInCart(form)) {
                            catalogService.addProductToCart(form);
                        }
                        break;
                    case "decreasing":
                        products =  catalogService.getAllProductsDecreasing();
                        request.setAttribute("products", products);
                        request.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
                        break;
                    case "increasing":
                        products =  catalogService.getAllProductsIncreasing();
                        request.setAttribute("products", products);
                        request.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
                        break;
                    case "new":
                        products =  catalogService.getAllProductsNew();
                        request.setAttribute("products", products);
                        request.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
