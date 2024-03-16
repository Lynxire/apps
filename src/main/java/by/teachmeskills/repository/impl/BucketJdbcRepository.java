package by.teachmeskills.repository.impl;

import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.BucketInterfaceRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BucketJdbcRepository implements BucketInterfaceRepository {
    JDBCConnection connection = new PostgreSQL();
    @SneakyThrows
    @Override
    public void add(Long orderId, Long productId) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatementMax = connect.prepareStatement("select max(id) from apps.bucket");
        ResultSet resultSet = preparedStatementMax.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatementCount = connect.prepareStatement("select quantity from apps.products where id = ?");
        preparedStatementCount.setLong(1, productId);
        ResultSet resultProducts = preparedStatementCount.executeQuery();
        resultProducts.next();
        int quantity = resultProducts.getInt(1);
        PreparedStatement preparedStatement = connect.prepareStatement( " insert into apps.bucket(id, orderid ,productid, count)  " +
                "values (?,?,?,?)");

        preparedStatement.setLong(1, ++maxId);
        preparedStatement.setLong(2, orderId);
        preparedStatement.setLong(3, productId);
        preparedStatement.setLong(4, --quantity);
        preparedStatement.executeUpdate();
    }
}
