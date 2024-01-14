package org.example;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        ExecutorService senderExecutor = Executors.newFixedThreadPool(3);
        ExecutorService receiverExecutor = Executors.newFixedThreadPool(3);

        for(int i=0;i<3;i++){
            senderExecutor.execute(new MessageSender(messageQueue));
        }

        for(int i=0;i<3;i++){
            receiverExecutor.execute(new MessageReceiver(messageQueue));
        }
        senderExecutor.shutdown();
        receiverExecutor.shutdown();

    }
}