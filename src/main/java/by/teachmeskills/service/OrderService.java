package by.teachmeskills.service;

import by.teachmeskills.api.bucket.BucketResponse;
import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.entity.Order;
import by.teachmeskills.entity.Product;
import by.teachmeskills.mapper.BucketMapper;
import by.teachmeskills.mapper.OrderMapper;
import by.teachmeskills.repository.BucketInterfaceRepository;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.ProductInterfaceRepository;
import by.teachmeskills.repository.impl.BucketJdbcRepository;
import by.teachmeskills.repository.impl.orders.OrderJdbcRepository;
import by.teachmeskills.repository.impl.product.ProductJdbcRepository;

import java.util.List;

public class OrderService {

    public OrderResponse addUserByOrder(Long userId){
        OrderInterfaceRepository repository = new OrderJdbcRepository();
        OrderMapper orderMapper = new OrderMapper();
        Order order = repository.add(userId);
        return orderMapper.toResponse(order);
    }

//    public OrderResponse addGoodToOrder(Long orderId, Long productId, Long count){
//        BucketInterfaceRepository repository = new BucketJdbcRepository();
//        repository.add(orderId,productId,count);
//        List<Bucket> buckets=repository.getBucketsByOrderId(orderId);
//        ProductUpdate productUpdate=new ProductUpdate();
//        List<Long> goodIds=buckets.stream().map(bucket -> bucket.getProductId()).toList();
//        List<ProductResponse> productResponses=productUpdate.getProductsByIds(goodIds);
//        OrderInterfaceRepository orderInterfaceRepository=new OrderJdbcRepository();
//        Order order=orderInterfaceRepository.getById(orderId);
//        OrderMapper orderMapper=new OrderMapper();
//        OrderResponse orderResponse=orderMapper.toResponse(order);
//        orderResponse.setProducts(productResponses);
//        return orderResponse;
//    }
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

    public OrderResponse allOrders(Long userId, Long productId){
        OrderInterfaceRepository orderJdbcRepository = new OrderJdbcRepository();
        BucketInterfaceRepository bucketRepository = new BucketJdbcRepository();
        ProductUpdate productUpdate = new ProductUpdate();
        List<Bucket> getBucketsByProductId = bucketRepository.getBucketsByProductId(productId);
        List<Long> list = getBucketsByProductId.stream().map(Bucket::getProductId).toList();
        List<ProductResponse> productsByIds = productUpdate.getProductsByIds(list);
        Order byId = orderJdbcRepository.getById(userId);
        OrderMapper orderMapper=new OrderMapper();
        OrderResponse orderResponse=orderMapper.toResponse(byId);
        orderResponse.setProducts(productsByIds);
        return orderResponse;


    }
}
