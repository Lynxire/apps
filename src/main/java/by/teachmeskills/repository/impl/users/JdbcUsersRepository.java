package by.teachmeskills.repository.impl.users;

import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.entity.User;
import by.teachmeskills.repository.UserInterfaceRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    @SneakyThrows
    @Override
    public Collection<User> allUsers() {
        Connection connect = connection.getConnect();
        List<User> users = new ArrayList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from apps.users ");
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
    public void deleteById(Long Id) {

    }

    @SneakyThrows
    @Override
    public User findID(Long id) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM apps.users WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        while(resultSet.next()){
            String idUsers = resultSet.getString("id");
            String name = resultSet.getString("name");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");

            user.setId(Long.valueOf(idUsers));
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(role);
        }
        return user;

    }


}
