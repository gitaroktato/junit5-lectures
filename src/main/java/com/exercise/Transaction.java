package com.exercise;

public class Transaction {

    private String id;
    private int amount;

    public Transaction(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
