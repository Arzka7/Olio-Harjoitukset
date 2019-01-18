package com.example.app;

import java.util.ArrayList;

public class TheaterList {
    private ArrayList<Theater> theater_list = new ArrayList<Theater>();

    public void addTheater(Theater theater) {
        theater_list.add(theater);
    }

    public ArrayList<Theater> getTheaterList() {
        return theater_list;
    }

    public Theater getTheaterByIndex(int index) {
        return theater_list.get(index);
    }

}
