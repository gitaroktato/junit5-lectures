package com.exercise;

public class LedgerService {

    public static void registerTransfer(Account from, Account to, int amount) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            // NONE
        }
        throw new RuntimeException("Request timed out");
    }

}
