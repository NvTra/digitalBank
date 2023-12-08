package com.tranv.fx22252.models;

import com.tranv.fx22252.utils.Utils;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    private final long serialVersionUID = 2L;
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String time;
    private final boolean status;
    private final TransactionType type;

    public Transaction(String accountNumber, double amount, boolean status, TransactionType type) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.time = Utils.getDateTime();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void displayTransactionHistory() {
        System.out.printf("%-4s    %6s | %-9s | %18s | %19s \n", this.status ? "[GD]" : "[FA]"
                , accountNumber
                , type
                , (type.equals(TransactionType.DEPOSIT) ? Utils.formatBalance(amount) : Utils.formatBalance(-amount))
                , time);
    }
}
