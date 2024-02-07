package by.teachmeskills;

import by.teachmeskills.entity.User;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.ShopRepository;

public class Main {
    public static void main(String[] args) {
        ShopRepository fileRepository = new FileRepository();
        User user = new User(1L,"Yara","Smit", "Pupa", "1998");
        User user1 = new User(2L,"Yara","Smit", "Pupa", "1998");
        fileRepository.add(user);
        fileRepository.add(user1);
        fileRepository.deleteById(1L);
        fileRepository.allUsers().forEach(System.out::println);



    }
}