package by.teachmeskills.controller.users;

import by.teachmeskills.api.products.ProductRequest;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.api.users.UserResponse;
import by.teachmeskills.entity.User;
import by.teachmeskills.service.ProductUpdate;
import by.teachmeskills.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.util.Collection;

public class UsersController {
    @SneakyThrows
    public void registration(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("regName");
        String email = req.getParameter("regEmail");
        String login = req.getParameter("regLogin");
        String password = req.getParameter("regPassword");
        if(email.isEmpty() || login.isEmpty() || password.isEmpty() || name.isEmpty()){
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        UserRequest userRequest = new UserRequest();
        UserService userService = new UserService();
        userRequest.setName(name);
        userRequest.setPassword(password);
        userRequest.setEmail(email);
        userRequest.setLogin(login);
        userService.registration(userRequest);
    }
    @SneakyThrows
    public void authentication(HttpServletRequest req, HttpServletResponse resp){
        String email = req.getParameter("logEmail");
        String password = req.getParameter("logPassword");
        if(email.isEmpty()||password.isEmpty()){
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        User user = new User();
        UserService userService = new UserService();
        user.setEmail(email);
        user.setPassword(password);
        User authentication = userService.authentication(user);
        HttpSession session = req.getSession();
        session.setAttribute("user", authentication);

        if(authentication.getRole().equals("Admin")){
            req.getRequestDispatcher("/jsp/admin/admin.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
        }

    }
    @SneakyThrows
        public void all(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        Collection<UserResponse> users = userService.all();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/jsp/admin/UserUpdate.jsp").forward(req, resp);
    }

    @SneakyThrows
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("idUser");
        if (parameter.isEmpty() || parameter.equals("0")) {
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        UserService userService = new UserService();
        UserResponse id = userService.search(Long.valueOf(parameter));
        req.setAttribute("idUsers", id);
        req.getRequestDispatcher("/jsp/admin/UserUpdate.jsp").forward(req, resp);
    }
    @SneakyThrows
    public void     add(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if(login.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()){
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        UserRequest userRequest = new UserRequest();
        UserService userService = new UserService();
        userRequest.setLogin(login);
        userRequest.setPassword(password);
        userRequest.setName(name);
        userRequest.setEmail(email);
        userService.add(userRequest);
        req.getRequestDispatcher("/jsp/admin/UserUpdate.jsp").forward(req, resp);


    }

    @SneakyThrows
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("idUser");
        if (parameter.isEmpty()) {
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        UserService userService = new UserService();
        userService.deleteById(Long.valueOf(parameter));
        req.getRequestDispatcher("/jsp/admin/UserUpdate.jsp").forward(req, resp);


    }
}
