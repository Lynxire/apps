package by.teachmeskills.repository;

import by.teachmeskills.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserInterfaceRepository {
    public void add(User user);
    public void deleteById(Long Id);
    public Collection<User> allUsers();

    public User findID(Long id);
}
