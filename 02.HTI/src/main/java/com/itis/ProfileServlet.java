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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private CrudRepositoryJdbsImpl crudRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.crudRepository = (CrudRepositoryJdbsImpl) config.getServletContext().getAttribute("CrudRepositoryJdbsImpl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            String username = (String) req.getSession().getAttribute("user");
            Optional<User> user = crudRepository.findByName(username);
            if (user.isPresent()) {
                req.setAttribute("username", username);
                req.setAttribute("password", user.get().getPassword());
                req.getRequestDispatcher("Profile.ftl").forward(req, resp);
            }
        }
    }
}
