package by.teachmeskills.controller;

import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.ShopRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShopRepository repository = new FileRepository();
        req.setAttribute("users", repository.allUsers());
        req.getRequestDispatcher("/jsp/AllUsers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
