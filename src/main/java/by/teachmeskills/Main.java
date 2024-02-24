package by.teachmeskills;

import by.teachmeskills.entity.Product;
import by.teachmeskills.repository.FileRepository;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.ProductRepository;
import by.teachmeskills.repository.UserInterfaceRepository;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UserInterfaceRepository repository = new FileRepository();
        System.out.println(repository.allUsers());


    }
}