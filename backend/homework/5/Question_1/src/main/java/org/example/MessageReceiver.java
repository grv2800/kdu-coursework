package org.example;
import java.util.logging.Logger;

public class MessageReceiver implements Runnable {
    Logger logger=Logger.getLogger(MessageReceiver.class.getName());
    MessageQueue messageQueue;
    public MessageReceiver(MessageQueue messageQueue){
        this.messageQueue=messageQueue;
    }

    public void run(){
            String message = null;
            try {
                message = messageQueue.getMessage();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            String ans="Message Recieved from " + message;

            logger.info(ans);
    }
}
