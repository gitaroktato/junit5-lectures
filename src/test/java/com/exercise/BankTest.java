package com.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    public void testTransfer() throws NotAllowedException {
        var from = new Account();
        from.addTransaction(new Transaction("", 10000));
        var to = new Account();
        var bank = new Bank(new LedgerService() {
            @Override
            public void registerTransfer(Account from, Account to, int amount) {
                //
            }
        });
        bank.transfer(from, to, 3000);
        System.out.println(from.getBalance());
        System.out.println(to.getBalance());
        bank.transfer(from, to, 10000);
    }

}