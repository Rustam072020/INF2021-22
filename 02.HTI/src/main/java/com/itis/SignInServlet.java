package com.itis;

import com.itis.models.User;
import com.itis.repository.CrudRepositoryJdbsImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private CrudRepositoryJdbsImpl crudRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.crudRepository = (CrudRepositoryJdbsImpl) config.getServletContext().getAttribute("CrudRepositoryJdbsImpl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("SignIn.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> user = crudRepository.findByName(username);
        if (user.isPresent()&&user.get().getPassword().equals(password)) {
            req.getSession().setAttribute("user", username);
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/signIn");
        }

    }
}
