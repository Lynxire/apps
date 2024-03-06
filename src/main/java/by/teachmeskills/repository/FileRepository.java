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
import java.util.Optional;

public class FileRepository implements UserInterfaceRepository {

    @Override
    public void add(User user) {
        List<User> userList = deserializable();
        long l = userList.stream().mapToLong(User::getId).max().orElse(0);
        user.setId(l+1);
        userList.add(user);
        serializable(userList);


    }

    @Override
    public void deleteById(Long userId) {
        List<User> userList = deserializable();
        userList.removeIf(user -> user.getId().equals(userId));
        serializable(userList);
    }

    @Override
    public Collection<User> allUsers() {
        return deserializable();
    }

    @Override
    public User findID(Long id) {
        Optional<User> list = allUsers().stream().filter(user -> user.getId().equals(id))
                .findFirst();

        if (!list.isPresent()) {
            return null;
        }
        return list.get();
    }

//    @Override
//    public void usersEdit(Long id, String name, String login, String password, String email) {
//        List<User> userList = findID(id);
//        if (userList != null && !userList.isEmpty()) {
//            User userToUpdate = userList.get(0);
//
//
//            userToUpdate.setName(name);
//            userToUpdate.setLogin(login);
//            userToUpdate.setPassword(password);
//            userToUpdate.setEmail(email);
//
//            serializable(userList);
//        }
//    }

    public void serializable(Object object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Java\\apps\\src\\main\\resources\\test.txt"))) {
            oos.writeObject(object);
            oos.close();


        } catch (Exception ex) {
            throw new RuntimeException("Сериализация пользователей не выполнилась " + ex);
        }

    }

    public List<User> deserializable() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Java\\apps\\src\\main\\resources\\test.txt"))) {

            return (List<User>) ois.readObject();


        } catch (Exception ex) {
            System.out.println(("Десериализация пользователей не выполнилась " + ex));
            return new ArrayList<>();

        }

    }
}
