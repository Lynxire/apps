package by.teachmeskills.repository.impl.product;

import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.ProductInterfaceRepository;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductJdbcRepository implements ProductInterfaceRepository {

    JDBCConnection connection = new PostgreSQL();

    @SneakyThrows
    @Override
    public void add(Object product) {
        Connection connect = connection.getConnect();
        Product products = (Product) product;
        PreparedStatement preparedStatementMax = connect.prepareStatement("select max(id) from apps.products");
        ResultSet resultSet = preparedStatementMax.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatement = connect.prepareStatement(" insert into apps.products(id, code ,\"name\", sum, quantity)  " +
                "values (?,?,?,?,?)");
        preparedStatement.setLong(1, ++maxId);
        preparedStatement.setInt(2, products.getCode());
        preparedStatement.setString(3, products.getName());
        preparedStatement.setInt(4, products.getSum());
        preparedStatement.setInt(5, products.getQuantity());
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void deleteById(Long Id) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatement = connect.prepareStatement("DELETE From apps.products WHERE id = ?");
        preparedStatement.setLong(1, Id);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public Collection<Product> allProduct() {
        Connection connect = connection.getConnect();
        List<Product> products = new ArrayList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from apps.products ");
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            int code = resultSet.getInt("code");
            String name = resultSet.getString("name");
            int sum = resultSet.getInt("sum");
            int quantity = resultSet.getInt("quantity");


            Product product = new Product(Long.valueOf(id), code, name, sum, quantity);
            products.add(product);
        }
        return products;
    }

    //    PreparedStatement pstmt =
//            conn.prepareStatement("select * from employee where id in (?)");
//    Array array = conn.createArrayOf("VARCHAR", new Object[]{"1", "2","3"});
//pstmt.setArray(1, array);
//    ResultSet rs = pstmt.executeQuery();
//    SELECT * FROM apps.products WHERE id in () ?
    @SneakyThrows
    public List<Product> getProductsByIds(List<Long> ids) {
        List<Product> products = new ArrayList<>();
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM apps.products WHERE id = ANY(?)");
        Array array = connect.createArrayOf("NUMERIC", ids.toArray());
        preparedStatement.setArray(1, array);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            int code = resultSet.getInt("code");
            String name = resultSet.getString("name");
            int sum = resultSet.getInt("sum");
            int quantity = resultSet.getInt("quantity");


            Product product = new Product(Long.valueOf(id), code, name, sum, quantity);
            products.add(product);
        }
        return products;
    }


    @SneakyThrows
    @Override
    public Product findByID(Long id) {
        Connection connect = connection.getConnect();
        PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM apps.products WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = new Product();
        while (resultSet.next()) {
            String idProducts = resultSet.getString("id");
            int code = resultSet.getInt("code");
            String name = resultSet.getString("name");
            int sum = resultSet.getInt("sum");
            int quantity = resultSet.getInt("quantity");

            product.setId(Long.valueOf(idProducts));
            product.setCode(code);
            product.setName(name);
            product.setSum(sum);
            product.setQuantity(quantity);

        }
        return product;
    }
}
