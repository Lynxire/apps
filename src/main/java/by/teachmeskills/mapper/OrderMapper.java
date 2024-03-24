package by.teachmeskills.mapper;

import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.Order;
import by.teachmeskills.entity.Product;

public class OrderMapper {

    public OrderResponse toResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }
}
