package org.example;
import org.example.Logger.Logging;
import org.example.Services.VehicleService;
import org.example.entities.Vehicle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.Services");

        VehicleService vehicleService = context.getBean(VehicleService.class);
        Vehicle mostExpensiveVehicle = vehicleService.findMostExpensiveVehicle();

        Logging.Mylogger("Details of the Most Expensive Vehicle:","info");
        String tyre="Tyre Brand: " + mostExpensiveVehicle.getTyre().getBrand();
        Logging.Mylogger(tyre,"info");
        String brand="Speaker Brand: " + mostExpensiveVehicle.getSpeaker().getBrand();
        Logging.Mylogger(brand,"info");
        String price="Total Price: " + mostExpensiveVehicle.getPrice();
        Logging.Mylogger(price,"info");

        context.close();
    }
}