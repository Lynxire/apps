package by.teachmeskills.config.impl;

import by.teachmeskills.config.JDBCConnection;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

    public class PostgreSQL implements JDBCConnection {
    @Override
    @SneakyThrows
    public Connection getConnect() {
        String url = "jdbc:postgresql://localhost:5432/shop";
        String username = "postgres";
        String password = "1111";//
        Class.forName("org.postgresql.Driver");//       Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}


