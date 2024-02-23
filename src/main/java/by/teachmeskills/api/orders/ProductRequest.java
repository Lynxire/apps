package by.teachmeskills.api.orders;

import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    private int code;
    private String name;
    private int sum;
    private int quantity;
}
