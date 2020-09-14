package com.example.cart;

import java.time.LocalTime;

public class ListItem {

    private final String id;
    private final String name;

    public ListItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
