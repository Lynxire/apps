package by.teachmeskills.repository.impl.product;

import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.OrderInterfaceRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements OrderInterfaceRepository {
    @Override
    public void add(Object object) {
        List<Product> productList = deserializable();
        long l = productList.stream().mapToLong(Product::getId).max().orElse(0);
        ((Product) object).setId(l+1);
        productList.add((Product) object);
        serializable(productList);
    }

    @Override
    public void deleteById(Long Id) {
        List<Product> productList = deserializable();
        productList.removeIf(order -> order.getId().equals(Id));
        serializable(productList);
    }

    @Override
    public Collection<Product> allProduct() {
        return deserializable();
    }

    @Override
    public Product findByID(Long id) {
        Optional<Product> first = allProduct().stream().filter(product -> product.getId().equals(id))
                .findFirst();

        if (!first.isPresent()) {
            return null;
        }
        return first.get();
    }


    public void serializable(Object object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Java\\apps\\src\\main\\resources\\orders.txt"))) {
            oos.writeObject(object);
            oos.close();


        } catch (Exception ex) {
            throw new RuntimeException("Сериализация пользователей не выполнилась " + ex);
        }

    }

    public List<Product> deserializable() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Java\\apps\\src\\main\\resources\\orders.txt"))) {
            return (List<Product>) ois.readObject();


        } catch (Exception ex) {
            System.out.println(("Десериализация пользователей не выполнилась " + ex));
            return new ArrayList<>();

        }

    }
}
