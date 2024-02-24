package by.teachmeskills.controller.users;

import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

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
        UserRequest userRequest = new UserRequest();
        UserService userService = new UserService();
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        req.setAttribute("email", userRequest.getEmail());
        boolean authentication = userService.authentication(userRequest);
        if (!authentication){
            req.getRequestDispatcher("/html/Eror.html").forward(req, resp);
        }
        req.getRequestDispatcher("/jsp/Hello.jsp").forward(req, resp);

    }
}
