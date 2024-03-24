package by.teachmeskills;

import by.teachmeskills.repository.impl.users.FileRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UserInterfaceRepository repository = new FileRepository();
        System.out.println(repository.allUsers());
        System.out.println(repository.allUsers());



    }
}