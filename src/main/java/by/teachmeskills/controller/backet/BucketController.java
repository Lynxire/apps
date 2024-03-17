package by.teachmeskills.controller.backet;

import by.teachmeskills.api.BucketResponse;
import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.entity.User;
import by.teachmeskills.service.OrderService;
import by.teachmeskills.service.ProductUpdate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

public class BucketController {
    BucketResponse bucketResponse;

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
        OrderService orderService = new OrderService();
        OrderResponse orderResponse = orderService.addUserByOrder(user.getId());
        bucketResponse = orderService.addOrderByBucket(orderResponse, Long.valueOf(idProduct), Long.valueOf(ProductCount));
        req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
    }
    //Не реализовано
    @SneakyThrows
    public void makeOrder(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        OrderService orderService = new OrderService();
        orderService.makeOrder(user.getId(), bucketResponse.getOrderId(), bucketResponse.getProductId(), bucketResponse.getCount());
        req.getRequestDispatcher("/jsp/client/bucket.jsp").forward(req, resp);
    }
}
