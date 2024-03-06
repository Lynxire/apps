package by.teachmeskills.controller.productController;

import by.teachmeskills.api.products.ProductRequest;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.service.ProductUpdate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.Collection;

public class ProductController{
    @SneakyThrows
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("id");
        if (parameter.isEmpty() || parameter.equals("0")) {
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        ProductUpdate productUpdate = new ProductUpdate();
        ProductResponse id = productUpdate.search(Long.valueOf(parameter));
        req.setAttribute("id", id);
        req.getRequestDispatcher("/jsp/admin/ProductUpdate.jsp").forward(req, resp);
    }

    @SneakyThrows
    public void add(HttpServletRequest req, HttpServletResponse resp) {
        String sum = req.getParameter("sum");
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String quantity = req.getParameter("quantity");
        if(sum.isEmpty() || code.isEmpty() || name.isEmpty() || quantity.isEmpty()){
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        ProductRequest order = new ProductRequest();
        ProductUpdate productUpdate = new ProductUpdate();
        order.setSum(Integer.parseInt(sum));
        order.setCode(Integer.parseInt(code));
        order.setName(name);
        order.setQuantity(Integer.parseInt(quantity));
        productUpdate.add(order);
        req.getRequestDispatcher("/jsp/admin/ProductUpdate.jsp").forward(req, resp);


    }

    @SneakyThrows
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("id");
        if (parameter.isEmpty()) {
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        ProductUpdate productUpdate = new ProductUpdate();
        productUpdate.deleteById(Long.valueOf(parameter));
        req.getRequestDispatcher("/jsp/admin/ProductUpdate.jsp").forward(req, resp);


    }

    @SneakyThrows
    public void all(HttpServletRequest req, HttpServletResponse resp) {
        ProductUpdate productUpdate = new ProductUpdate();
        Collection<ProductResponse> products = productUpdate.all();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/admin/ProductUpdate.jsp").forward(req, resp);
    }@SneakyThrows
    public void allClient(HttpServletRequest req, HttpServletResponse resp) {
        ProductUpdate productUpdate = new ProductUpdate();
        Collection<ProductResponse> products = productUpdate.all();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
    }
}
