package by.teachmeskills.controller;

import by.teachmeskills.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if(session == null || user == null){
            req.setAttribute("error","Авторизируйтесь");
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, response);
        }

        if (!user.getRole().equals("Admin")){
            req.setAttribute("error","недостаточно прав");
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, response);

        }
        else {
            chain.doFilter(request,response);
        }
    }
}
