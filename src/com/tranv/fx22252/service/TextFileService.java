package com.tranv.fx22252.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> readFileTxt(String fileName) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                List<String> rows = new ArrayList<>();
                Collections.addAll(rows, values);
                data.add(rows);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại");
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
        }
        return data;
    }
}
