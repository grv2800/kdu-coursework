package org.example;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger=Logger.getLogger(Main.class.getName());

        int number=6;
        Factorial factorial=new Factorial(number);
        Factors factors=new Factors(number);

        factorial.start();
        factors.start();

        try {
            factorial.join();
            factors.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Main method getting terminated");
    }
}