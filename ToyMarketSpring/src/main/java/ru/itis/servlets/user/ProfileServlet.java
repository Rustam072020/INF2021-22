package ru.itis.servlets.user;

import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.user.ProfileServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/in/profile")
public class ProfileServlet extends HttpServlet {

    ProfileServiceImpl profileService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.profileService = applicationContext.getBean(ProfileServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =(String) request.getSession().getAttribute("email");
        if (email == null) {
            response.sendRedirect("/signIn");
        } else {
            User user = profileService.getUser(email);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        }
    }

}
