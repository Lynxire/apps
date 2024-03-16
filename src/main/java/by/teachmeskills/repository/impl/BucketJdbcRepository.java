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
    public void add(Long orderId, Long productId, Long count) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatementMax = connect.prepareStatement("select max(id) from apps.bucket");
        ResultSet resultSet = preparedStatementMax.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatement = connect.prepareStatement( " insert into apps.bucket(id, orderid ,productid, count)  " +
                "values (?,?,?,?)");

        preparedStatement.setLong(1, ++maxId);
        preparedStatement.setLong(2, orderId);
        preparedStatement.setLong(3, productId);
        preparedStatement.setLong(4, count);
        preparedStatement.executeUpdate();

        PreparedStatement quantity = connect.prepareStatement("SELECT quantity from apps.products WHERE id = ?");
        quantity.setLong(1, productId);
        ResultSet executeQuery = quantity.executeQuery();
        executeQuery.next();
        long quantityLong = executeQuery.getLong(1);
        if(quantityLong < 1){
            throw new RuntimeException("Товар закончился");
        }

        PreparedStatement preparedStatementCount = connect.prepareStatement("UPDATE apps.products SET quantity = ? where id = ?");
        preparedStatementCount.setLong(1,--quantityLong);
        preparedStatementCount.setLong(2,productId);
        preparedStatementCount.executeUpdate();




    }
}
