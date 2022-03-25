package ru.itis.servlets.product;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.ProductsForm;
import ru.itis.dto.ProfileProductForm;
import ru.itis.services.products.CatalogServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private CatalogServiceImpl catalogService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.catalogService = applicationContext.getBean(CatalogServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductsForm product = catalogService.getProduct(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
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
                        doGet(request, response);
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
                        doGet(request, response);
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
