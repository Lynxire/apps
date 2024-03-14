package by.teachmeskills.repository;

import by.teachmeskills.entity.Order;

import java.util.Collection;

public interface OrderInterfaceRepository {
    public void add(Long userId, Long cost);
}
