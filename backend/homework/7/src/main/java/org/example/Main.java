package org.example;
import org.example.inventoryConfig.inventoryStore;
import org.example.logger.Logging;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
   public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.inventoryConfig","org.example.services");

    public static inventoryStore inventorystore = context.getBean(inventoryStore.class);
    public static void main(String[] args) {
        mostExpensive();
        leastExpensive();
        context.close();
    }
    public static void mostExpensive(){

        Logging.Mylogger("Details of the Most Expensive Vehicle:","info");
        String tyre="Tyre Brand: " + inventorystore.mostExpensiveVehicle().getTyre().getBrand();
        Logging.Mylogger(tyre,"info");
        String brand="Speaker Brand: " + inventorystore.mostExpensiveVehicle().getSpeaker().getBrand();
        Logging.Mylogger(brand,"info");
        String price="Total Price: " + inventorystore.mostExpensiveVehicle().getPrice();
        Logging.Mylogger(price,"info");
    }
    public static void leastExpensive(){
        Logging.Mylogger("Details of the Most Expensive Vehicle:","info");
        String tyre="Tyre Brand: " + inventorystore.leastExpensiveVehicle().getTyre().getBrand();
        Logging.Mylogger(tyre,"info");
        String brand="Speaker Brand: " + inventorystore.leastExpensiveVehicle().getSpeaker().getBrand();
        Logging.Mylogger(brand,"info");
        String price="Total Price: " + inventorystore.leastExpensiveVehicle().getPrice();
        Logging.Mylogger(price,"info");
    }
}