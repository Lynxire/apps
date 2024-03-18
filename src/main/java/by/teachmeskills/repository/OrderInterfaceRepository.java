package by.teachmeskills.repository;

import by.teachmeskills.entity.Order;
import by.teachmeskills.entity.Product;

import java.util.Collection;
import java.util.List;

public interface OrderInterfaceRepository {
    public Order add(Long userId);

}
