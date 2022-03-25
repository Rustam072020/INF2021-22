package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/in/*")
public class PersonFilter implements Filter {

    private final static String PAGE_PERSON_COOKIE_NAME = "pagePerson";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if (!processPersonCookie(request)) {
            response.sendRedirect("/signIn");
            return;
        }
        chain.doFilter(request, response);
    }

    private Boolean processPersonCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(PAGE_PERSON_COOKIE_NAME)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}

