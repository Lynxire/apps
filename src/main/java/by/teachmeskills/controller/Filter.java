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
        if(session == null){
            req.getRequestDispatcher("/html/Eror.html").forward(req, response);
        }

        User user = (User) session.getAttribute("user");
        String authentication=req.getParameter("logSubmit");
        if (authentication!=null){
            chain.doFilter(request,response);
        } else if(user == null || !user.getRole().equals("Admin")){
            req.getRequestDispatcher("/html/Eror.html").forward(req, response);
        }
        else {
            chain.doFilter(request,response);
        }
    }
}
