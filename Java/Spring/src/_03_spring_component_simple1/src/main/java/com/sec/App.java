package com.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
    @Autowired
    Person person;

    public void run(String...strings){
        System.out.println(person.getBook());
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
