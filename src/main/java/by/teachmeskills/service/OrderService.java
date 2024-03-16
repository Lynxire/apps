package by.teachmeskills.service;

import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.entity.Order;
import by.teachmeskills.entity.Product;
import by.teachmeskills.mapper.OrderMapper;
import by.teachmeskills.mapper.ProductMapper;
import by.teachmeskills.repository.BucketInterfaceRepository;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.ProductInterfaceRepository;
import by.teachmeskills.repository.impl.BucketJdbcRepository;
import by.teachmeskills.repository.impl.orders.OrderJdbcRepository;
import by.teachmeskills.repository.impl.product.ProductJdbcRepository;

public class OrderService {
    public OrderResponse addUserByOrder(Long userId){
        OrderInterfaceRepository repository = new OrderJdbcRepository();
        OrderMapper orderMapper = new OrderMapper();
        Order order = repository.add(userId);
        return orderMapper.toResponse(order);
    }
    public void addOrderByBucket(OrderResponse orderResponse, Long productId){
        Long orderId = orderResponse.getId();
        BucketInterfaceRepository repository = new BucketJdbcRepository();
        repository.add(orderId, productId);
    }
}
