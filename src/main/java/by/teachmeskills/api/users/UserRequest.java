package by.teachmeskills.api.users;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
}
