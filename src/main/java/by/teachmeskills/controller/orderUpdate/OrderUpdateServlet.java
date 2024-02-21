package by.teachmeskills.controller.orderUpdate;

import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.OrderRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class OrderUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("search") != null) {
            search(req, resp);
        } else if (req.getParameter("add") != null) {
            add(req, resp);
        } else if (req.getParameter("delete") != null) {
            delete(req, resp);
        } else if (req.getParameter("all") != null) {
            all(req, resp);
        }

    }

    @SneakyThrows
    private void search(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("id");
        if (parameter.isEmpty() || parameter.equals("0")) {
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        OrderInterfaceRepository repository = new OrderRepository();
        List<Product> id = repository.findID(Long.valueOf(parameter));
        req.setAttribute("id", id);
        req.getRequestDispatcher("/jsp/OrderUpdate.jsp").forward(req, resp);
    }

    @SneakyThrows
    private void add(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/jsp/AddOrder.jsp").forward(req, resp);


    }

    @SneakyThrows
    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("id");
        if (parameter.isEmpty() || parameter.equals("0")) {
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        OrderInterfaceRepository repository = new OrderRepository();
        repository.deleteById(Long.valueOf(parameter));
        req.getRequestDispatcher("/jsp/OrderUpdate.jsp").forward(req, resp);


    }

    @SneakyThrows
    private void all(HttpServletRequest req, HttpServletResponse resp) {
        OrderInterfaceRepository repository = new OrderRepository();
        Collection<Product> products = repository.allProduct();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/OrderUpdate.jsp").forward(req, resp);
    }


}
