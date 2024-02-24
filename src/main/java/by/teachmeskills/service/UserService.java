package by.teachmeskills.service;

import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.api.users.UserResponse;
import by.teachmeskills.entity.User;
import by.teachmeskills.mapper.UserMapper;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.util.Optional;
import java.util.stream.Stream;

public class UserService {
    public boolean authentication(UserRequest userRequest) {
        UserInterfaceRepository repository = new FileRepository();
        UserMapper userMapper = new UserMapper();
        Optional<User> optional = repository.allUsers().stream().filter(user1 -> user1.getEmail().equals(userRequest.getEmail()))
                .filter(user -> user.getPassword().equals(userRequest.getPassword()))
                .findFirst();
        if(optional.isPresent()){
            return true;
        }
        return false;
    }

    public UserResponse registration(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        UserInterfaceRepository repository = new FileRepository();
        repository.add(user);
        return userMapper.toResponse(user);
    }
}
