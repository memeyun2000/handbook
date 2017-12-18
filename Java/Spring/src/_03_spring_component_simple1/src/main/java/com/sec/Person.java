package com.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    private Book book;
    private String name;
    private int age;

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}