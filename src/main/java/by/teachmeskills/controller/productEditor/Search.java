package by.teachmeskills.controller.productEditor;

import by.teachmeskills.entity.User;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.ProductRepository;
import by.teachmeskills.repository.ShopRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("id");
        if(parameter.isEmpty() || parameter.equals("0")){
            req.getRequestDispatcher("/html/Eror.html").forward(req,resp);
        }
        ShopRepository repository = new ProductRepository();
        List<User> id = repository.findID(Long.valueOf(parameter));
        req.setAttribute("id", id);
        req.getRequestDispatcher("/jsp/ProductEditor.jsp").forward(req,resp);
    }


}
