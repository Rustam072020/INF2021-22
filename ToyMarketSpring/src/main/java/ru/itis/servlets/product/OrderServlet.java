package ru.itis.servlets.product;
import org.springframework.context.ApplicationContext;
import ru.itis.services.products.ShoppingCartServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/in/order")
public class OrderServlet extends HttpServlet {

    private ShoppingCartServiceImpl shoppingCartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.shoppingCartService = applicationContext.getBean(ShoppingCartServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =(String) request.getSession().getAttribute("email");
        request.setAttribute("products", shoppingCartService.getAllProducts(email));
        request.setAttribute("sumCost", shoppingCartService.sumAllCostProduct(email));
        request.getRequestDispatcher("/WEB-INF/views/order-form.jsp").forward(request,response);
    }
}
