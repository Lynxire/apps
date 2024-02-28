package by.teachmeskills.service;

import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.api.users.UserResponse;
import by.teachmeskills.entity.User;
import by.teachmeskills.mapper.UserMapper;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.util.Collection;
import java.util.Optional;

public class UserService {
    public boolean authentication(User user) {
        UserInterfaceRepository repository = new FileRepository();
        Optional<User> optional = repository.allUsers().stream().filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .filter(user2 -> user2.getPassword().equals(user.getPassword()))
                .findFirst();
        return optional.isPresent();
    }

    public void registration(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        UserInterfaceRepository repository = new FileRepository();
        Optional<User> optional = repository.allUsers().stream().filter(user1 -> user1.getLogin().equals(userRequest.getLogin()))
                .findFirst();
        if(optional.isPresent()){
            throw new RuntimeException("Пользователь с таким логином существует");
        }
        repository.add(user);
    }

    public Collection<UserResponse> all(){
        UserInterfaceRepository repository = new FileRepository();
        UserMapper userMapper = new UserMapper();
        Collection<UserResponse> list = repository.allUsers().stream().map(userMapper::toResponse).toList();

        return list;
    }
}
