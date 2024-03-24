package by.teachmeskills.controller.backet;

import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.entity.Product;
import by.teachmeskills.entity.User;
import by.teachmeskills.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

public class BucketController {


    @SneakyThrows
    public void addOrderByBucket(HttpServletRequest req, HttpServletResponse resp) {
        String idProduct = req.getParameter("idProduct");
        String ProductCount = req.getParameter("ProductCount");
        if (idProduct.isEmpty() || idProduct.equals("0")) {
            req.setAttribute("error","Неверный ID товара");
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        session.setAttribute("productId", Long.valueOf(idProduct));
        OrderService orderService = new OrderService();
        orderService.addOrderByBucket(user.getId(), Long.valueOf(idProduct), Long.valueOf(ProductCount));
        req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
    }
    @SneakyThrows
    public void makeOrder(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        OrderService orderService = new OrderService();
        orderService.makeOrder(user.getId());
        req.getRequestDispatcher("/jsp/client/bucket.jsp").forward(req, resp);
    }
    @SneakyThrows
    public void allOrders(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        OrderService orderService = new OrderService();
        OrderResponse orderResponse = orderService.allOrders(user.getId());
        session.setAttribute("orders", orderResponse);
        req.getRequestDispatcher("/jsp/client/bucket.jsp").forward(req, resp);

    }

    @SneakyThrows
    public void cleanBucket(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        OrderService orderService = new OrderService();
        orderService.cleanBucket(user.getId());
        session.getAttribute("orders");
        req.getRequestDispatcher("/jsp/client/bucket.jsp").forward(req, resp);
    }
}
