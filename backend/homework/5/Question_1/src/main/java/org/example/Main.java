package org.example;

public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        Thread[] senders=new Thread[3];
        Thread[] recievers=new Thread[3];

        for(int i=0;i<2;i++){
            senders[i]= new Thread(new MessageSender(messageQueue));
            senders[i].setName(String.format("Thread number: %d",i));
            senders[i].start();
        }

        for(int i=0;i<3;i++){
            recievers[i]= new Thread(new MessageReceiver(messageQueue));
            String temp="Thread number:"+i;
            recievers[i].setName(temp);
            recievers[i].start();
        }

    }
}