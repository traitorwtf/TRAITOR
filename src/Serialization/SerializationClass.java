package Serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by traitorwtf on 19.03.2017.
 */
public class SerializationClass {
    static ArrayList<String> person = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("C:\\Users\\traitorwtf\\IdeaProjects\\traitor\\src\\Serialization\\Database.ser");
        if (file.exists()){         deserialize();}
        Person prsn = new Person();
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите имя: ");
        prsn.setName(scn.nextLine());
        System.out.println("Введите фамилию: ");
        prsn.setSurname(scn.nextLine());
        person.add(prsn.toString());
        System.out.println(person.toString());
        serialize(person);
    }

    private static void serialize(Object object) {
        try {
            File file = new File("C:\\Users\\traitorwtf\\IdeaProjects\\traitor\\src\\Serialization\\Database.ser");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
            } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void deserialize() {
        try {
            File file = new File("C:\\Users\\traitorwtf\\IdeaProjects\\traitor\\src\\Serialization\\Database.ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            person = (ArrayList<String>) objectIn.readObject();
            objectIn.close();
            fileIn.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
