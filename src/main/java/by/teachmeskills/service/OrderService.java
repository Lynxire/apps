package by.teachmeskills.service;

import by.teachmeskills.api.bucket.BucketResponse;
import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.entity.Order;
import by.teachmeskills.mapper.BucketMapper;
import by.teachmeskills.mapper.OrderMapper;
import by.teachmeskills.repository.BucketInterfaceRepository;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.impl.BucketJdbcRepository;
import by.teachmeskills.repository.impl.orders.OrderJdbcRepository;

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
//        Order order=orderInterfaceRepository.getOrderByUserid(orderId);
//        OrderMapper orderMapper=new OrderMapper();
//        OrderResponse orderResponse=orderMapper.toResponse(order);
//        orderResponse.setProducts(productResponses);
//        return orderResponse;
//    }
    public BucketResponse addOrderByBucket(Long userId, Long productId, Long count){
        OrderInterfaceRepository orderInterfaceRepository = new OrderJdbcRepository();
        Order orderByUserid = orderInterfaceRepository.getOrderByUserid(userId);


        Long id = 0L;
        if(orderByUserid.getUserId() == userId && orderByUserid.getUserId() != null && orderByUserid.getStatus().equals("Создан")){
            id = orderByUserid.getId();
        }
        else {
            OrderResponse orderResponse = addUserByOrder(userId);
            id = orderResponse.getId();
        }
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

    public OrderResponse allOrders(Long userId){
        OrderInterfaceRepository orderJdbcRepository = new OrderJdbcRepository();
        Order orderByUserid = orderJdbcRepository.getOrderByUserid(userId);
        BucketInterfaceRepository bucketRepository = new BucketJdbcRepository();
        Long getOrderId = 0L;
        if(orderByUserid.getStatus().equals("Создан")){
            getOrderId = orderByUserid.getId();
        }
        if(getOrderId == null) {
            throw new RuntimeException("Корзина пустая");
        }
        List<Bucket> bucketsByOrderId = bucketRepository.getBucketsByOrderId(getOrderId);
        List<Long> listProductId = bucketsByOrderId.stream().map(bucket -> bucket.getProductId()).toList();
        ProductService productService = new ProductService();
        List<ProductResponse> productsByIds = productService.getProductsByIds(listProductId);
        OrderMapper orderMapper=new OrderMapper();
        OrderResponse orderResponse=orderMapper.toResponse(orderByUserid);
        orderResponse.setProducts(productsByIds);
        return orderResponse;

    }

    public void cleanBucket(Long userId){
        OrderInterfaceRepository orderJdbcRepository = new OrderJdbcRepository();
        Order orderByUserid = orderJdbcRepository.getOrderByUserid(userId);
        BucketInterfaceRepository bucketRepository = new BucketJdbcRepository();
        Long getOrderId = orderByUserid.getId();
        List<Bucket> bucketsByOrderId = bucketRepository.getBucketsByOrderId(getOrderId);
        List<Long> listProductId = bucketsByOrderId.stream().map(bucket -> bucket.getProductId()).toList();
        List<Long> listCount = bucketsByOrderId.stream().map(Bucket::getCount).toList();


        bucketRepository.сleanBucket(getOrderId,listProductId, listCount);



    }
}
