package org.example;

public class Main {


    public static void main(String[] args) {
        ExecuteTransaction executeTransaction=new ExecuteTransaction();
        Thread thread=new Thread(executeTransaction);
        thread.start();
    }





}