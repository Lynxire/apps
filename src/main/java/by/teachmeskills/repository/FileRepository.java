package by.teachmeskills.repository;

import by.teachmeskills.entity.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FileRepository implements ShopRepository{

    @Override
    public void add(Object user) {
        List<User> userList = deserializable();
        userList.add((User) user);
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
    public List<User> findID(Long id) {
        List<User> list = allUsers().stream().filter(user -> id.equals(user.getId()))
                .toList();

        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    public void serializable(Object object){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\fined\\IdeaProjects\\apps\\src\\main\\resources\\test.txt")))
        {
           oos.writeObject(object);
           oos.close();


        }
        catch(Exception ex){

            throw new RuntimeException("Вышло исключение: " + ex);
        }

    }
    public List<User> deserializable(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\fined\\IdeaProjects\\apps\\src\\main\\resources\\test.txt")))
        {
            return (List<User>) ois.readObject();


        }
        catch(Exception ex){

            throw new RuntimeException("Вышло исключение: " + ex);

        }

    }
}
