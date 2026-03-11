package com.xx.中大机试;

public class Tiger extends Cat {
    int data;

    public Tiger(int data) {
        super(data);
        this.data = data;
        System.out.println("Constructor of Tiger is Running " + data);
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        Tiger dc =  new Tiger(6);
        Tiger dc2 =  new Tiger(18);
    }

}
