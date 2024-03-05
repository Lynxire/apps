package by.teachmeskills.api.order;

import lombok.Data;
@Data
public class OrderResponse {
    Long id;
    Long userId;
    Long cost;
    String status;
}