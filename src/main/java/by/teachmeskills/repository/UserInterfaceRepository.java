package by.teachmeskills.repository;

import by.teachmeskills.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserInterfaceRepository {
    public void add(User user);
    public void deleteById(Long Id);
    public Collection<User> allUsers();

    public List<User> findID(Long id);
    public void usersEdit(Long id, String name, String login, String password, String email);
}
