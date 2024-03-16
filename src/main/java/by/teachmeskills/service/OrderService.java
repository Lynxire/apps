package by.teachmeskills.service;

import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.entity.Order;
import by.teachmeskills.entity.Product;
import by.teachmeskills.mapper.OrderMapper;
import by.teachmeskills.mapper.ProductMapper;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.ProductInterfaceRepository;
import by.teachmeskills.repository.impl.orders.OrderJdbcRepository;
import by.teachmeskills.repository.impl.product.ProductJdbcRepository;

public class OrderService {
    public void addUserByOrder(Long userId){
        OrderInterfaceRepository repository = new OrderJdbcRepository();
        repository.add(userId);
    }
}
