package by.teachmeskills.service;

import by.teachmeskills.api.users.UserRequest;
import by.teachmeskills.api.users.UserResponse;
import by.teachmeskills.entity.User;
import by.teachmeskills.mapper.UserMapper;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserService {
    public User authentication(User user) {
        UserInterfaceRepository repository = new FileRepository();
        List<User> list = repository.allUsers().stream().filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .filter(user2 -> user2.getPassword().equals(user.getPassword()))
                .toList();
        if (list.isEmpty()) {
            throw new RuntimeException("Пользователь не найден");
        }

        return list.get(0);
    }

    public void registration(UserRequest userRequest) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userRequest);
        UserInterfaceRepository repository = new FileRepository();
        Optional<User> optional = repository.allUsers().stream().filter(user1 -> user1.getLogin().equals(userRequest.getLogin()))
                .findFirst();
        Optional<User> optional1 = repository.allUsers().stream().filter(user1 -> user1.getRole().equals("Admin"))
                .findFirst();

        if (optional.isPresent()) {
            throw new RuntimeException("Пользователь с таким логином существует");
        }
        if (!optional1.isPresent()) {
            user.setRole("Admin");
        }else {
            user.setRole("Client");
        }
        repository.add(user);
    }

    public Collection<UserResponse> all() {
        UserInterfaceRepository repository = new FileRepository();
        UserMapper userMapper = new UserMapper();
        Collection<UserResponse> list = repository.allUsers().stream().map(userMapper::toResponse).toList();

        return list;
    }
}
