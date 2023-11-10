package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static <T> void serializeObjectList(List <T> data, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
            System.out.println("Serialized data is saved in " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> deserializeData(String filename) {
        List<T> data = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            data = (List<T>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
