package by.teachmeskills.repository;

import by.teachmeskills.entity.Product;

import java.util.Collection;

public interface ProductInterfaceRepository {
    public void add(Object object);
    public void deleteById(Long Id);
    public Collection<Product> allProduct();

    public Product findByID(Long id);
}
