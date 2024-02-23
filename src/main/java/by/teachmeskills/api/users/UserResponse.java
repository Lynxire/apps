package by.teachmeskills.api.users;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
}
