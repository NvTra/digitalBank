package com.tranv.fx22252.models;

import java.text.DecimalFormat;
import java.util.List;

public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    @Override
    public void displayInformation() {
        DecimalFormat df = new DecimalFormat("#,### đ");
        System.out.printf("%-14s |%20s | %8s | %15s", this.getCustomerId(), this.getName(), this.isCustomerPremium() ? "PREMIUM" : "Normal", df.format(getTotalBalance()));
        List<Account> accounts = this.getAccounts();
        if (!accounts.isEmpty()) {
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println();
                System.out.printf("%-2s%12s |%20s |%27s", i + 1, accounts.get(i).getAccountNumber(), accounts.get(i).getAccountType(), df.format(accounts.get(i).getBalance()));
            }
        }
        System.out.println();
    }

    @Override
    public void displayTransactionInformation() {

        System.out.printf("%12s | %18s | %19s | %36s \n", "Account", "Amount", "Time", "Transaction ID");
        for (Account a : getAccounts()) {
            if (a.getTransactionList() != null) {
                for (Transaction t : a.getTransactionList()) {
                    t.displayTransactionHistory();
                    System.out.println();
                }
            }
        }
    }
}
