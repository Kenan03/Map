package com.company;

import java.io.*;
import java.util.*;

public class Library {

    public class Book{
        private final String bookName;
        private final String authorName;
        private final int year;

        public Book(String bookname, String author, int year) {
            this.bookName = bookname;
            this.authorName = author;
            this.year = year;
        }

        public String toString()
        {
            return this.bookName + " " + this.authorName + " " + this.year;
        }

    }
    private ArrayList<Book> data;

    public Library() {
        this.data = new ArrayList<Book>();
    }

    public void add(String bookName, String authorName, int year)
    {
        data.add(new Book(bookName, authorName, year));
    }

    public Book find(String bookName, String authorName, int year)
    {
        Book tmp = new Book(bookName, authorName, year);
        for(Book idx: this.data)
        {
            if(Objects.equals(idx.bookName,tmp.bookName) && Objects.equals(idx.authorName, tmp.authorName) && idx.year == tmp.year) {
                return tmp;
            }
        }
        return null;
    }
    public Book[] findYear(int year)
    {
        ArrayList<Book> tmp = new ArrayList<>();
        for(Book idx: this.data)
        {
            if(idx.year > year)
                tmp.add(idx);

        }
        return tmp.toArray(tmp.toArray(new Book[0]));
    }
    public void loadFromTextFile(String filename) {
        File file = new File("D:\\Java project\\Map\\" + filename);
        String author = "";
        String book = "";
        int year = 0;
        boolean flagName = false;
        boolean flagString = false;
        Scanner scanner;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (scanner.hasNextLine()) {
                    if(!flagName)
                    {
                        book = scanner.next();
                    }
                    else{
                        author = scanner.next();
                    }
                    flagName = true;
                }
                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                    flagString = true;
                }
                if (flagString && author.length() > 0 && book.length() > 0 && year > 0) {
                    //this.setting.put(key, value);
                    this.data.add(new Book(book, author, year));
                    flagName = false;
                    //scanner.nextLine();
                    flagString = false;
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
            for(Book idx: this.data)
                buffer.write(idx.bookName + " " + idx.authorName + " " + idx.year + "\n");
            buffer.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
//
//public class Main {
//
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//
//        String textPath = "input.txt";
//        String textPath1 = "output.txt";
//        Library r = new Library();
//        r.loadFromTextFile(textPath);
//        r.saveToTextFile(textPath1);
//        //System.out.println(r.find("name2", "author2", 2222));
//        r.add("BookName5", "Author5", 1877);
//        System.out.println(Arrays.toString(r.findYear(1850)));
//        r.saveToTextFile(textPath1);
//
//    }
//}
