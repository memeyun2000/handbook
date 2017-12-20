package com.sec;

import org.springframework.core.task.TaskExecutor;

public class TaskExecutorExample {

    private class MessagePrinterTask implements Runnable {

        private String message;
        private long sleepTime;

        public MessagePrinterTask(String message,long sleepTime) {
            this.message = message;
            this.sleepTime = sleepTime;
        }

        public void run() {
            try{
                System.out.println(message + " begin");
                Thread.sleep(sleepTime);
                System.out.println(message + " end");
            } catch(Exception e) {

            }
            
        }

    }
    
    private TaskExecutor taskExecutor;

    public TaskExecutorExample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessages() {
        for(int i = 0; i < 30; i++) {
            System.out.println("put " + i + " task to queue");
            taskExecutor.execute(new MessagePrinterTask("Message" + i,i*3000));
        }
    }

}