package by.teachmeskills.mapper;

import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.api.users.UserResponse;
import by.teachmeskills.entity.User;

public class UserMapper {
    public User toEntity(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setLogin(userRequest.getLogin());
        user.setId(userRequest.getId());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setLogin(user.getLogin());
        userResponse.setId(user.getId());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }
}
