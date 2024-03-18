package by.teachmeskills.service;

import by.teachmeskills.api.bucket.BucketResponse;
import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.entity.Order;
import by.teachmeskills.mapper.BucketMapper;
import by.teachmeskills.mapper.OrderMapper;
import by.teachmeskills.repository.BucketInterfaceRepository;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.impl.BucketJdbcRepository;
import by.teachmeskills.repository.impl.orders.OrderJdbcRepository;

public class OrderService {

    public OrderResponse addUserByOrder(Long userId){
        OrderInterfaceRepository repository = new OrderJdbcRepository();
        OrderMapper orderMapper = new OrderMapper();
        Order order = repository.add(userId);
        return orderMapper.toResponse(order);
    }
    public BucketResponse addOrderByBucket(Long userId, Long productId, Long count){
        OrderResponse orderResponse = addUserByOrder(userId);
        Long id = orderResponse.getId();
        BucketInterfaceRepository repository = new BucketJdbcRepository();
        if(id == 0 || productId == 0 || count == 0){
            throw new RuntimeException("Неверные значения в полях");
        }
        Bucket bucket = repository.add(id, productId, count);
        BucketMapper bucketMapper = new BucketMapper();
        return bucketMapper.toResponse(bucket);


    }
    public void makeOrder(Long userId){
        BucketInterfaceRepository repository = new BucketJdbcRepository();
        repository.makeOrder(userId);

    }
}
