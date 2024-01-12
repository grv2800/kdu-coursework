package org.example;
import java.util.logging.Logger;
public class Factorial extends Thread{
    private int number;
    Logger logger=Logger.getLogger(Factorial.class.getName());
    public Factorial(int number){
        this.number=number;
    }

    @Override
    public void run() {
        long factorial=calculateFactorial(number);
        String temp="Factorial of the given number is :"+factorial;
        logger.info(temp);
    }
    long calculateFactorial(int n){
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }

}
