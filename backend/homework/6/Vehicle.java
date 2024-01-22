package org.example.entities;

public class Vehicle {
    Speaker speaker;
    Tyre tyre;
    int price;
    public Vehicle(Speaker speaker, Tyre tyre, int price){
        this.speaker=speaker;
        this.tyre=tyre;
        this.price=price;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public int getPrice() {
        return price;
    }

}
