package ru.itis.servlets.product;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.ProfileProductForm;
import ru.itis.services.products.ShoppingCartServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/in/cart")
public class ShoppingCartServlet extends HttpServlet {

    private ShoppingCartServiceImpl shoppingCartService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.shoppingCartService = applicationContext.getBean(ShoppingCartServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =(String) request.getSession().getAttribute("email");
        if (email == null) {
            response.sendRedirect("/signIn");
        } else {
            request.setAttribute("products", shoppingCartService.getAllProducts(email));
            request.setAttribute("sumCost", shoppingCartService.sumAllCostProduct(email));
            request.getRequestDispatcher("/WEB-INF/views/shopping-cart.jsp").forward(request, response);
        }    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String email = (String) request.getSession().getAttribute("email");
        int id = 0;
        ProfileProductForm form;
        if (action != null) {
            switch (action) {
                case "dropProduct":
                    id = Integer.parseInt(request.getParameter("id"));
                    form = ProfileProductForm.builder()
                            .id(id)
                            .email(email)
                            .build();
                    shoppingCartService.dropProduct(form);
                    doGet(request, response);
                    break;
                default:
                    return;
            }
        }

    }


}
