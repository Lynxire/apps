package by.teachmeskills.controller;

import by.teachmeskills.entity.User;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.ShopRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        req.setAttribute("name", name);
        req.getRequestDispatcher("/jsp/Hello.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        User user = new User();
        user.setId(Long.valueOf(login));
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        ShopRepository file = new FileRepository();
        file.add(user);


        System.out.println(file.allUsers());
    }
}
