package by.teachmeskills.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Product implements Serializable {
    private Long id;
    private int code;
    private String name;
    private int sum;
    private int quantity;

}
