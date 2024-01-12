package org.example;

public class MessageSender implements Runnable{
    MessageQueue messageQueue=new MessageQueue();
    public MessageSender(MessageQueue messageQueue){

        this.messageQueue=messageQueue;
    }
    public void run(){
            String message="Sender"+": "+Thread.currentThread().getName();
            try {
                messageQueue.addMessage(message);
            } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
            }

    }
}
