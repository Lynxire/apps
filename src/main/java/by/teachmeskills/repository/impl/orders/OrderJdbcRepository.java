package by.teachmeskills.repository.impl.orders;

import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.repository.OrderInterfaceRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderJdbcRepository implements OrderInterfaceRepository {
    JDBCConnection connection = new PostgreSQL();

    @SneakyThrows
    @Override
    public void add(Long userId, Long cost) {
        Connection con = connection.getConnect();
        PreparedStatement preparedStatementMaxId = con.prepareStatement("SELECT max(id) from apps.orders where ?");
        ResultSet resultSet = preparedStatementMaxId.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatement = con.prepareStatement( " insert into apps.orders(id, userId, status)  " +
                "values (?,?,?)");

        preparedStatement.setLong(1, ++maxId);
        preparedStatement.setLong(2, userId);
        preparedStatement.setString(3,"Создан");
        preparedStatement.executeUpdate();

    }

}
