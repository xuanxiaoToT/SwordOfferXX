package com.xx.中大机试;

public class Cat {
    public int data;

    public Cat() {
        System.out.println("Default Constructor of Cat is Running");
    }

    public Cat(int data) {
        this.data = data;
        System.out.println("Constructor of Cat is Running " + data);
    }

    private int ggg(){
        return 0;
    }
}
