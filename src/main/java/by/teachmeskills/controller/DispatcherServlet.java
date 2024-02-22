package by.teachmeskills.controller;

import by.teachmeskills.controller.productController.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductController productController = new ProductController();
        if (req.getParameter("search") != null) {
            productController.search(req, resp);
        } else if (req.getParameter("add") != null) {
            productController.add(req, resp);
        } else if (req.getParameter("delete") != null) {
            productController.delete(req, resp);
        } else if (req.getParameter("all") != null) {
            productController.all(req, resp);
        }

    }





}
