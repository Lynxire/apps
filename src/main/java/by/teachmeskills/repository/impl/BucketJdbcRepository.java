package by.teachmeskills.repository.impl;

import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.repository.BucketInterfaceRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BucketJdbcRepository implements BucketInterfaceRepository {
    JDBCConnection connection = new PostgreSQL();
    Connection connect = connection.getConnect();


    @SneakyThrows
    @Override
    public Bucket add(Long orderId, Long productId, Long count) {
        PreparedStatement preparedStatementMax = connect.prepareStatement("select max(id) from apps.bucket");
        ResultSet resultSet = preparedStatementMax.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatement = connect.prepareStatement(" insert into apps.bucket(id, orderid ,productid, count)  " +
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
        if (quantityLong < 1) {
            throw new RuntimeException("Товар закончился");
        }
        PreparedStatement preparedStatementCount = connect.prepareStatement("UPDATE apps.products SET quantity = ? where id = ?");
        preparedStatementCount.setLong(1, quantityLong - count);
        preparedStatementCount.setLong(2, productId);
        preparedStatementCount.executeUpdate();
        Bucket bucket = new Bucket();
        bucket.setId(maxId);
        bucket.setOrderId(orderId);
        bucket.setProductId(productId);
        bucket.setCount(count);

        timerDeleteOrdersByBucket(orderId, count, productId);

        return bucket;

    }

    @SneakyThrows
    @Override
    public void makeOrder(Long userId) {
        PreparedStatement preparedStatement = connect.prepareStatement("UPDATE apps.orders SET status = ? where userid = ?");
        preparedStatement.setString(1, "Оформлен");
        preparedStatement.setLong(2, userId);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public List<Bucket> getBucketsByOrderId(Long orderId) {
        Connection con = connection.getConnect();
        PreparedStatement preparedStatementMaxId = con.prepareStatement("SELECT * from apps.bucket WHERE orderid = ?");
        preparedStatementMaxId.setLong(1, orderId);
        ResultSet resultSet = preparedStatementMaxId.executeQuery();
        List<Bucket> buckets = new ArrayList<>();
        while (resultSet.next()) {
            Bucket bucket = new Bucket();
            Long id = resultSet.getLong("id");
            Long orderid = resultSet.getLong("orderid");
            Long productid = resultSet.getLong("productid");
            Long count = resultSet.getLong("count");
            bucket.setId(id);
            bucket.setOrderId(orderid);
            bucket.setProductId(productid);
            bucket.setCount(count);
            buckets.add(bucket);

        }
        return buckets;

    }


    @SneakyThrows
    public void timerDeleteOrdersByBucket(Long orderId, Long count, Long productId) {
        final String tableDeleteBucket = "DELETE FROM apps.bucket where orderid = ?";
        final String tableDeleteOrders = "DELETE FROM apps.orders where id = ?";
        final String selectCountProduct = "UPDATE apps.products SET quantity = ? where id = ?";

        PreparedStatement preparedStatementStatus = connect.prepareStatement("select status from apps.orders WHERE id = ?");
        preparedStatementStatus.setLong(1, orderId);
        ResultSet status = preparedStatementStatus.executeQuery();
        status.next();
        String statusString = status.getString(1);

        PreparedStatement quantity = connect.prepareStatement("SELECT quantity from apps.products WHERE id = ?");
        quantity.setLong(1, productId);
        ResultSet executeQuery = quantity.executeQuery();
        executeQuery.next();
        long quantityLong = executeQuery.getLong(1);

        PreparedStatement preparedStatementProduct = connect.prepareStatement(selectCountProduct);
        preparedStatementProduct.setLong(1, quantityLong + count);
        preparedStatementProduct.setLong(2, productId);


        PreparedStatement preparedStatementBucket = connect.prepareStatement(tableDeleteBucket);
        preparedStatementBucket.setLong(1, orderId);
        PreparedStatement preparedStatementOrders = connect.prepareStatement(tableDeleteOrders);
        preparedStatementOrders.setLong(1, orderId);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @SneakyThrows
            public void run() {
                if (statusString.equals("Создан") && statusString != null) {
                    preparedStatementProduct.executeUpdate();
                    preparedStatementBucket.executeUpdate();
                    preparedStatementOrders.executeUpdate();
                }
            }
        };
        long time = 7200000L;
        timer.schedule(task, time);
    }

    @SneakyThrows
    @Override
    public void сleanBucketByOrderId(Long orderId, Long productId, Long count) {
        final String tableDeleteBucket = "DELETE FROM apps.bucket where orderid = ?";
        final String tableDeleteOrders = "DELETE FROM apps.orders where id = ?";
        final String selectCountProduct = "UPDATE apps.products SET quantity = ? where id = ?";


        PreparedStatement preparedStatementBucket = connect.prepareStatement(tableDeleteBucket);
        preparedStatementBucket.setLong(1, orderId);
        preparedStatementBucket.executeUpdate();

        PreparedStatement preparedStatementOrders = connect.prepareStatement(tableDeleteOrders);
        preparedStatementOrders.setLong(1, orderId);
        preparedStatementOrders.executeUpdate();



    }

}
