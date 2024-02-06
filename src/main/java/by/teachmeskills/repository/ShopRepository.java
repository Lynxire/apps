package by.teachmeskills.repository;

import by.teachmeskills.entity.User;

import java.util.Collection;

public interface ShopRepository {
    public void add(User user);
    public void deleteById(Long userId);
    public Collection<User> allUsers();
}
