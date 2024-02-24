package by.teachmeskills.service;

import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.entity.User;
import by.teachmeskills.mapper.UserMapper;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.util.Optional;

public class UserService {
    public boolean authentication(User user) {
        UserInterfaceRepository repository = new FileRepository();
        UserMapper userMapper = new UserMapper();
        Optional<User> optional = repository.allUsers().stream().filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .filter(user2 -> user.getPassword().equals(user.getPassword()))
                .findFirst();
        if(optional.isPresent()){
            return true;
        }
        return false;
    }

    public void registration(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        UserInterfaceRepository repository = new FileRepository();
        repository.add(user);
    }
}
