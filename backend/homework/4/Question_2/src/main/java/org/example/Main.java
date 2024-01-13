package org.example;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    // Set up a logger for the MainDriver class
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        TicketReservation ticketReservation = new TicketReservation();

        boolean bookingResult1 = ticketReservation.bookFlight("Mark", "Doe", 30, "Male", "business", "ABC123");
        boolean bookingResult2 = ticketReservation.bookFlight("Shawn", "Jacobs", 25, "Female", "economy", "XYZ456");
        boolean bookingResult3 = ticketReservation.bookFlight("Bob", "Smith", 35, "Male", "economy", "123XYZ");

        for (String s : Arrays.asList("Booking Result 1: {}" + bookingResult1, "Booking Result 2: {}" + bookingResult2, "Booking Result 3: {} " + bookingResult3, "Confirmed List: " + ticketReservation.getConfirmedList(), "Waiting List: " + ticketReservation.getWaitingList())) {
            LOGGER.info(s);
        }

        LOGGER.info("Confirmed List: " + ticketReservation.getConfirmedList());
        LOGGER.info("Waiting List: " + ticketReservation.getWaitingList());

        boolean cancelResult = ticketReservation.cancel("ABC123");

        for (String s : Arrays.asList("Cancellation Result: " + cancelResult, "Confirmed List after Cancellation: " + ticketReservation.getConfirmedList(), "Waiting List after Cancellation: " + ticketReservation.getWaitingList())) {
            LOGGER.info(s);
        }

        LOGGER.info("Confirmed List after Cancellation: " + ticketReservation.getConfirmedList());
        LOGGER.info("Waiting List after Cancellation: " + ticketReservation.getWaitingList());
    }
}
