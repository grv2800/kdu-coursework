package org.example;

public class GenericMethod {
    public static <T> void generic(T[] arr, int index1, int index2){
        if(index1 <0 || index1>= arr.length || index2<0 || index2 >= arr.length){
            return;
        }
        T temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }
}
