package by.teachmeskills.repository;

import by.teachmeskills.entity.User;

import java.util.Collection;
import java.util.List;

public interface ShopRepository {
    public void add(Object user);
    public void deleteById(Long userId);
    public Collection<User> allUsers();

    public List<User> findID(Long id);
}
