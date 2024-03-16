package by.teachmeskills.controller.backet;

import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.User;
import by.teachmeskills.service.OrderService;
import by.teachmeskills.service.ProductUpdate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

public class BucketController {

    @SneakyThrows
    public void addOrderByBucket(HttpServletRequest req, HttpServletResponse resp) {
        String idProduct = req.getParameter("idProduct");
        String count = req.getParameter("ProductCount");
        if (idProduct.isEmpty() || idProduct.equals("0")) {
            req.setAttribute("error","Неверный ID товара");
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        OrderService orderService = new OrderService();
        orderService.addUserByOrder(user.getId());
        req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
    }
}
