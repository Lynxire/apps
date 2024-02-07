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

public class FileRepository implements ShopRepository{
    private List<User> userList = new ArrayList<>();

    @Override
    public void add(User user) {

        userList.add(user);
        serializable(userList);



    }

    @Override
    public void deleteById(Long userId) {
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId().equals(userId)){
                userList.remove(i);
            }

        }
    }
    @Override
    public Collection<User> allUsers() {
        return deserializable(userList);
    }

    public void serializable(Object object){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\main\\resources\\test.ser")))
        {
           oos.writeObject(object);
           oos.close();


        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

    }
    public Collection<User> deserializable(Object object){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\main\\resources\\test.ser")))
        {
            return (Collection<User>) ois.readObject();


        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }

    }
}
