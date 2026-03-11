package com.xx.中大机试;

import com.xx.Answer;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 图书管理
 */
public class LibraryManagement implements Answer {
    public static void main(String[] args) {
        new LibraryManagement().answer();
    }

    @Override
    public void answer() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("C_programming", 19.90));
        list.add(new Book("C++_tips", 39.00));
        list.add(new Book("Harry_Potter", 40.10));
        list.add(new Book("Stroy", 6.50));
        list.add(new Book("Mag", 6.50));
        list.add(new Book("C_programming", 29.90));
        System.out.println(sortBooks(list));
        // System.out.println(sortBooks2(list));

    }

    public List<Book> sortBooks(List<Book> books) {
        Comparator<Book> comparator = (a, b) -> {
            if (a.price == b.price) {
                return a.name.compareTo(b.name);
            } else {
                return (int) (a.price - b.price);
            }
        };
        books.sort(comparator);
        return books;
    }


    public List<Book> sortBooks2(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            Book min = null;
            int minIndex = -1;
            for (int j = i + 1; j < books.size(); j++) {
                Book book = books.get(j);
                if (min == null) {
                    min = book;
                    minIndex = j;
                } else {
                    if (min.price > book.price) {
                        min = book;
                        minIndex = j;
                        continue;
                    }
                    if (min.price == book.price && min.name.compareTo(book.name) > 0) {
                        min = book;
                        minIndex = j;
                    }
                }
            }
            if (minIndex != -1) {
                Book temp = books.get(i);
                books.set(i, min);
                books.set(minIndex, temp);
            }
        }
        return books;
    }


    @Override
    public Object initData() {
        return null;
    }

    @AllArgsConstructor
    public class Book {
        public String name;

        public double price;

        @Override
        public String toString() {
            return this.name + " " + this.price;
        }
    }
}
