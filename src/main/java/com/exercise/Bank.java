package com.exercise;

public class Bank {


    public void transfer(Account from, Account to, int amount) throws NotAllowedException {
        if (from.getBalance() < amount) {
            throw new NotAllowedException("Account is not eligible for transfer");
        }
        from.addTransaction(new Transaction("TRANSFER", -amount));
        to.addTransaction(new Transaction("TRANSFER", -amount));
        LedgerService.registerTransfer(from, to, amount);
    }

}
