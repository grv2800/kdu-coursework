package org.example;
import java.util.logging.Logger;
public class Factors extends Thread{
    private int number;
    Logger logger=Logger.getLogger(Factors.class.getName());
    public Factors(int number){
        this.number=number;
    }
    @Override
    public void run(){
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                logger.info(i + " ");
            }
        }
    }
}
