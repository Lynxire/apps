package by.teachmeskills;

import by.teachmeskills.entity.User;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.ShopRepository;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ShopRepository fileRepository = new FileRepository();
//        User user = new User(1L,"Yara","Smit", "Pupa", "1998");
//        User user1 = new User(2L,"Yara","Smit", "Pupa", "1998");
//        fileRepository.add(user);
//        fileRepository.add(user1);
//        fileRepository.deleteById(1L);
//        System.out.println(fileRepository.allUsers());
        ShopRepository shopRepository = new FileRepository();
        System.out.println(shopRepository.allUsers());
        System.out.println(shopRepository.findID(3L));


    }
}