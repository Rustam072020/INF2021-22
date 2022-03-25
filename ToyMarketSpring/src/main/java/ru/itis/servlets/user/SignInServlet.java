package ru.itis.servlets.user;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.SignInForm;
import ru.itis.services.user.SignInServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private static final String COOKIE_NAME = "isAuthenticated";

    SignInServiceImpl signInService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.signInService = applicationContext.getBean(SignInServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =(String) request.getSession().getAttribute("email");
        if (email != null) {
            response.sendRedirect("/in/profile");
        } else {
            request.getRequestDispatcher("/WEB-INF/views/sign-in.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
            SignInForm form = SignInForm.builder()
                    .email(request.getParameter("email"))
                    .password(request.getParameter("password"))
                    .build();
            if (!form.getEmail().isEmpty() && signInService.correctSignIn(form)) {
                signInService.createCookie(response, COOKIE_NAME);
                request.getSession().setAttribute("email", form.getEmail());
                response.sendRedirect("/catalog");
            } else {
                response.sendRedirect("/signIn");
            }
    }
}

