package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Settings {

    private Map<String, Integer> setting;


    public Settings() {
        this.setting = new HashMap<String, Integer>();
    }

    public void put(String name, int value) {
        this.setting.put(name, value);
    }

    public int get(String name) {
        return this.setting.get(name);
    }

    public void delete(String name) {
        this.setting.remove(name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> pair : this.setting.entrySet())
            sb.append(pair.getKey()).append(": ").append(pair.getValue()).append("\n");
        return sb.toString();
    }


    public boolean equals(Object objectValue) {
        if (this == objectValue)
            return true;
        if (!(objectValue instanceof Settings)) // тип объекта определяет
            return false;
        Settings cmpObject = (Settings) objectValue;
        return this.setting.equals(cmpObject.setting);
    }


    public void loadFromTextFile(String filename) {
        File file = new File("D:\\Java project\\Map\\" + filename);
        String key = "";
        boolean flagString = false;
        boolean flagInt = false;
        int value = 0;
        Scanner scanner;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (scanner.hasNextLine()) {
                    key = scanner.next();
                    flagString = true;
                }
                if (scanner.hasNextInt()) {
                    value = scanner.nextInt();
                    flagInt = true;
                }
                if (flagString && flagInt) {
                    //this.setting.put(key, value);
                    setting.put(key, value);
                    flagString = false;
                    flagInt = false;
                    //scanner.nextLine();
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Error! File not found.");
        } catch (InputMismatchException exception) {
            System.out.println("Incorrect format of input data!");
        }

    }

    public void saveToTextFile(String filename) {

        try {
            FileWriter file = new FileWriter("D:\\Java project\\Map\\" + filename);

            BufferedWriter buffer = new BufferedWriter(file);
            for (Map.Entry<String, Integer> pair : this.setting.entrySet())
                buffer.write(pair.getKey() + " " + pair.getValue() + "\n");
            buffer.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void saveToBinaryFile(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream("D:\\Java project\\Map\\" + filename);
            byte[] buffer;
            for (Map.Entry<String, Integer> i : this.setting.entrySet()) {
                String s = i.getKey() + " " + i.getValue() + "\n";
                buffer = s.getBytes();
                fos.write(buffer);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found. Try again!");
        } catch (IOException exception) {
            System.out.println("Cannot read data from file!");
        }
    }

    public void loadFromBinaryFile(String filename) throws FileNotFoundException {
        Scanner stream = new Scanner(new FileInputStream(filename));
        while (stream.hasNextLine()) {
            String setting = stream.nextLine();
            Scanner temp = new Scanner(setting);
            String key = temp.next();
            Integer value = temp.nextInt();
            if (!this.setting.containsKey(key))
                this.setting.put(key, value);
        }
    }

}


