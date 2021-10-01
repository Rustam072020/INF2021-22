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
import java.io.PrintWriter;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private CrudRepositoryJdbsImpl crudRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("SignUp.ftl").forward(req, resp);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.crudRepository = (CrudRepositoryJdbsImpl) config.getServletContext().getAttribute("CrudRepositoryJdbsImpl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = User.builder().name(username).password(password).build();
        crudRepository.save(user);
        req.getSession(true).setAttribute("user", username);
        resp.sendRedirect("/profile");
    }

}
