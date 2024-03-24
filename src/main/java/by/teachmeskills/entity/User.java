package by.teachmeskills.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Data
public class User implements Serializable{

    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String role;
    public  User(){

    }
    public User(Long id, String name, String login, String password, String email, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
