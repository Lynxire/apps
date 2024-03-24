package by.teachmeskills.entity;

import lombok.Data;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

@Data
public class Order {
    Long id;
    Long userId;
    String status;

    public Order(){

    }
    public Order(Long id, Long userId, String status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }
}

