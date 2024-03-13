package by.teachmeskills.repository;

import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.entity.User;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcUsersRepository implements UserInterfaceRepository {


    JDBCConnection connection = new PostgreSQL();


    @Override
    public void add(User user) {

    }

    @Override
    public void deleteById(Long Id) {

    }

    @Override
    @SneakyThrows
    public Collection<User> allUsers() {
        Connection connect = connection.getConnect();
        List<User> users = new ArrayList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from shop.users");
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");

            User user = new User();
            user.setId(Long.valueOf(id));
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(role);

            users.add(user);
        }
        return users;
    }

    @Override
    public User findID(Long id) {
        return null;
    }


}
