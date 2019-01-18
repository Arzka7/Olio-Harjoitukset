package com.example.app;

public class Theater {
    private String id;
    private String location;

    public Theater(String input_id, String input_location) {
        id = input_id;
        location = input_location;
    }

    public String getId() {
        return id;
    }

    public void setId(String input) {
        id = input;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String input) {
        location = input;
    }

    @Override
    public String toString() {
        return location;
    }
}