package by.teachmeskills.repository.impl.product;

import by.teachmeskills.config.JDBCConnection;
import by.teachmeskills.config.impl.PostgreSQL;
import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.ProductInterfaceRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
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
        PreparedStatement preparedStatement = connect.prepareStatement( " insert into apps.products(id, code ,\"name\", sum, quantity)  " +
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


            Product product = new Product(Long.valueOf(id), code, name,sum,quantity);
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
        while(resultSet.next()){
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
