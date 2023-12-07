package com.tranv.fx22252.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại");
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Đã có lỗi xảy ra: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Đã có lỗi xảy ra " + e.getMessage());
        }
        return objects;
    }

    public static <T> void writeFile(String fileName, List<T> objects) {
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object : objects) {
                file.writeObject(object);
            }
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}
