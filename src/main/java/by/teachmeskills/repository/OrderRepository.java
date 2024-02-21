package by.teachmeskills.repository;

import by.teachmeskills.entity.Product;
import by.teachmeskills.entity.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderRepository implements OrderInterfaceRepository {
    @Override
    public void add(Object object) {
        List<Product> productList = deserializable();
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
    public List<Product> findID(Long id) {
        List<Product> list = allProduct().stream().filter(product -> product.getId().equals(id))
                .toList();

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }


    public void serializable(Object object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\fined\\IdeaProjects\\apps\\src\\main\\resources\\orders.txt"))) {
            oos.writeObject(object);
            oos.close();


        } catch (Exception ex) {
            throw new RuntimeException("Сериализация пользователей не выполнилась " + ex);
        }

    }

    public List<Product> deserializable() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\fined\\IdeaProjects\\apps\\src\\main\\resources\\orders.txt"))) {
            return (List<Product>) ois.readObject();


        } catch (Exception ex) {
            System.out.println(("Десериализация пользователей не выполнилась " + ex));
            return new ArrayList<>();

        }

    }
}
