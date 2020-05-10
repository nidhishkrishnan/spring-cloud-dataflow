package com.codenotfound.tasks;

import java.util.Date;

public class RunnableTask implements Runnable{
    private String message;

    public RunnableTask(String message){
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(new Date()+" Runnable Task with "+message
                +" on thread "+Thread.currentThread().getName());
    }
}
