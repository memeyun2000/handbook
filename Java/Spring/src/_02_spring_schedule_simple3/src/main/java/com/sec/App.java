package com.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("测试任务调度开始..."); 
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring.xml");     
        TaskExecutorExample te = (TaskExecutorExample)appContext.getBean("taskExecutorExample");  
        te.printMessages();  
        System.out.println("--------"); 
    }
}
