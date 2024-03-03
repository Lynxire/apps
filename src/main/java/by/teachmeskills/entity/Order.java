package by.teachmeskills.entity;

import lombok.Data;

@Data
public class Order {
    Long id;
    Long userId;
    Long cost;
    String status;
}

