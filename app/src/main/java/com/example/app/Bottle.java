package com.example.app;

public class Bottle {

    private final String name;
    private final String manufacturer;
    private final double total_energy;
    private final double size;
    private final double price;

    public Bottle() {
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        size = 0.5;
        price = 1.80;
    }

    public Bottle(String b_name, String manuf, double totE, double b_size, double b_price) {
        name = b_name;
        manufacturer = manuf;
        total_energy = totE;
        size = b_size;
        price = b_price;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getEnergy() {
        return total_energy;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}