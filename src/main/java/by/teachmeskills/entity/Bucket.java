package by.teachmeskills.entity;

import lombok.Data;

@Data
public class Bucket {
    Long id;
    Long productId;
    Long orderId;
    Long count;
}
