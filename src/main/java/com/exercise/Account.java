package com.exercise;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Account {

    List<Transaction> transactions = new LinkedList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public int getBalance() {
        return getTransactions().stream()
                .mapToInt(Transaction::getAmount).sum();
    }
}
