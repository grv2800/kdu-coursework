package org.example;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
public class MessageQueue {

    BlockingQueue<String> msgQueue;
    Logger logger=Logger.getLogger(MessageQueue.class.getName());
    MessageQueue(){
        this.msgQueue=new LinkedBlockingQueue<>();
    }
    public synchronized void addMessage(String message) throws InterruptedException {
        msgQueue.put(message);
        notifyAll();
    }
    public synchronized String getMessage() throws InterruptedException {
        while (msgQueue.isEmpty()){
            String waitingMessage="Waiting...";
            logger.info(waitingMessage);
            wait();
        }
        return msgQueue.remove();
    }
}
