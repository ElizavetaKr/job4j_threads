package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Integer> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new FileInputStream(file)) {
            int data;
            while ((data = input.read()) > 0) {
                if (filter.test(data)) {
                    output.append((char) data);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Predicate<Integer> filterWithoutUnicode = n -> n < 0x80;
        Predicate<Integer> filter = n -> true;
    }
}