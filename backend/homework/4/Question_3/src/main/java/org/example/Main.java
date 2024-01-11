package org.example;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Integer[] intArray = {121, 241, 356, 344, 255};
        LOGGER.info("Original Integer Array: {}" + Arrays.toString(intArray));

        // Swap elements in the integer array
        GenericMethod.generic(intArray, 1, 3);
        LOGGER.info("Integer Array after swapping elements: {}" + Arrays.toString(intArray));
    }
}