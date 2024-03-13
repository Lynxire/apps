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
    @SneakyThrows
    @Override
    public void add(User user) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatementMax = connect.prepareStatement("select max(id) from apps.users");
        ResultSet resultSet = preparedStatementMax.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatement = connect.prepareStatement( " insert into apps.users(id, \"name\", login, password, email, role)  " +
                "values (?,?,?,?,?,?)");
        preparedStatement.setLong(1, ++maxId);
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getRole());
        preparedStatement.executeUpdate();
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

    @SneakyThrows
    @Override
    public void deleteById(Long Id) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatement = connect.prepareStatement("DELETE From apps.users WHERE id = ?");
        preparedStatement.setLong(1, Id);
        preparedStatement.executeUpdate();

    }

    @SneakyThrows
    @Override
    public User findByID(Long id) {
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
