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
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Autowired
    PersonRepository personDao;

    @Override
    public void run(String... args) throws Exception {
        Person p = new Person();
        p.setName("guoqy");
        p.setAddress("uru");
        p.setAge(28);
        personDao.save(p);

        System.out.println("success !!!");
    }
}
