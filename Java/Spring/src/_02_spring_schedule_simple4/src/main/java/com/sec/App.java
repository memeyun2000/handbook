package com.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ImportResource(locations={"classpath:spring.xml"})
public class App implements CommandLineRunner {
    @Autowired
    public TaskExecutorExample taskExecutorExample;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        taskExecutorExample.printMessages();
        System.out.println("-------");
        
    }
}
