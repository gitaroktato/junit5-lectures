package com.exercise;

public class Bank {

    private final LedgerService ledgerService;

    public Bank(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    public Bank() {
        this(new LedgerService());
    }


    public void transfer(Account from, Account to, int amount) throws NotAllowedException {
        if (from.getBalance() < amount) {
            throw new NotAllowedException("Account is not eligible for transfer for amount " + amount);
        }
        from.addTransaction(new Transaction("TRANSFER", -amount));
        to.addTransaction(new Transaction("TRANSFER", amount));
        ledgerService.registerTransfer(from, to, amount);
    }

}
