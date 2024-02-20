package by.teachmeskills;

import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.OrderRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OrderInterfaceRepository fileRepository = new OrderRepository();
//        User user = new User();
//        user.setId(3L);
//        user.setName("Yara");
//        user.setLogin("SMit");
//        user.setPassword("OO");
//        user.setEmail("lopa@mail.ru");
            Product user = new Product();
            user.setId(1L);
            user.setSum(100);
//        System.out.println(user);
//        User user1 = new User(2L,"Yara","Smit", "Pupa", "1998");
         fileRepository.add(user);
//        fileRepository.allUsers().forEach(System.out::println);
//        fileRepository.add(user1);
//        fileRepository.deleteById(1L);
//        System.out.println(fileRepository.allUsers());
//        UserInterfaceRepository shopRepository = new FileRepository();
//        System.out.println(shopRepository.allUsers());
//        FileRepository fileRepository1 = new FileRepository();
//        System.out.println(shopRepository.findID(1L));
        System.out.println(fileRepository.allProduct());
//        fileRepository1.usersEdit(3L, "dupa", "lupa", "123", "s@mail.ru");
//        System.out.println(shopRepository.findID(3L));


    }
}