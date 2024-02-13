package by.teachmeskills.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
@Data
public class User implements Serializable {

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;


}
