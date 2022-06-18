package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String textPath = "input.txt";
        String binPath = "settings.bin";
        String textPath1 = "output.txt";
        String binPath1 = "settings1.bin";

        Settings settings = new Settings();
        Settings settings1 = new Settings();

        settings.loadFromBinaryFile(binPath);
        settings.saveToBinaryFile(binPath1);
        System.out.println(settings + "--------");

        settings1.loadFromTextFile(textPath);
        settings1.saveToTextFile(textPath1);
        System.out.println(settings1 + "--------");


    }
}
