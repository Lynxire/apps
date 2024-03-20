package by.teachmeskills.api.order;

import by.teachmeskills.api.products.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    Long id;
    Long userId;
    String status;
    List<ProductResponse> products;
}