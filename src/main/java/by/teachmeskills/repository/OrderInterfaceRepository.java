package by.teachmeskills.repository;

import by.teachmeskills.entity.Order;

public interface OrderInterfaceRepository {
    public Order add(Long userId);

    public Order getOrderByUserid(Long userId);

}
