package org.example.entities;


public class Vehicle {
    private Speaker speaker;
    private Tyre tyre;
    private double price;
    private String factory;
    public Vehicle(Speaker speaker, Tyre tyre, double price,String factory){
        this.speaker=speaker;
        this.tyre=tyre;
        this.price=price;
        this.factory=factory;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public double getPrice() {
        return price;
    }
    public String getFactory(){return factory;}
}
