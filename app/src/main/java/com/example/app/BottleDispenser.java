package com.example.app;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class BottleDispenser {

    private int bottles;
    private ArrayList<Bottle> bottle_array;
    private double money;
    private static BottleDispenser bd = null;

    private BottleDispenser() {
        money = 0;

        bottle_array = new ArrayList();

        bottle_array.add(new Bottle());
        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", 0.5, 1.5, 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 0.5, 2.0));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 1.5, 2.5));
        bottle_array.add(new Bottle("Fanta Zero", "Fanta", 0.5, 0.5, 1.95));
        bottle_array.add(new Bottle("Fanta Zero", "Fanta", 0.5, 0.5, 1.95));
    }

    public static BottleDispenser getInstance() {
        if (bd == null) {
            bd = new BottleDispenser();
        }
        return bd;
    }

    private void deleteBottle(int i) {
        bottle_array.remove(i);
    }

    public int searchBottle(String name, String size) {
        for (Bottle bottle : bottle_array) {
            if (name.equals(bottle.getName()) && size.equals(Double.toString(bottle.getSize())) ){
                return bottle_array.indexOf(bottle);
            }
        }
        return -1;
    }

    public String addMoney(double money_in) {
        money += money_in;
        return String.format("Klink! Lisättiin %.2f€!\n", money_in);
    }

    public String buyBottle(int index) {
        if (index == -1) {
            return "Kyseinen tuote on loppu!";
        }
        String temp;
        Bottle bottle = (Bottle) bottle_array.get(index);
        if (money < bottle.getPrice()) {
            return String.format("Ei tarpeeksi rahaa! Tarvitset vielä %.2f€!", bottle.getPrice()-money);
        }
        else {
            temp = "KACHUNK! " + bottle.getName() + " " + bottle.getSize() + "L tipahti masiinasta!";
            money -= bottle.getPrice();
            deleteBottle(index);
            return temp;
        }
    }

    public String returnMoney() {
        String temp;
        temp = String.format("Klink klink. Sinne menivät rahat! Rahaa tuli ulos %.2f€\n", money);
        money = 0;
        return temp;
    }

    public void printBottles() {
        for (int i = 1; i <= bottles; i++) {
            System.out.println(i + ". Nimi: " + bottle_array.get(i-1).getName());
            System.out.print("\tKoko: " + bottle_array.get(i-1).getSize());
            System.out.println("\tHinta: " + bottle_array.get(i-1).getPrice());
        }
    }
}
