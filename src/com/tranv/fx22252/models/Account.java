package com.tranv.fx22252.models;

import com.tranv.fx22252.dao.CustomerDao;
import com.tranv.fx22252.dao.TransactionDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Account implements Serializable {
    private static final long serialVersionUID = 2L;
    private final String accountNumber;
    private double balance;
    private String accountType;
    private final String customerId;
    private final List<Transaction> transactionsList = TransactionDao.list();

    public Account(String customerId, String accountNumber, double balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;

    }


    public boolean isPremiumAccount() {
        return this.balance >= 10000000;
    }

    public Customer getCustomer() {
        List<Customer> customerList = CustomerDao.list();
        return customerList.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerId() {
        return customerId;
    }


    public void createTransaction(double amount, boolean status, TransactionType type) {
        List<Transaction> transactions = TransactionDao.list();
        if (type == TransactionType.DEPOSIT) {
            balance += amount;
        } else if (type == TransactionType.TRANSFER) {
            balance -= amount;
        } else if (type == TransactionType.WITHDRAW) {
            balance -= amount;
        }
        transactions.add(new Transaction(accountNumber, (amount == 0 ? balance : amount), status, type));
        TransactionDao.save(transactions);
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = TransactionDao.list();
        List<Transaction> transactionsByAccount = transactions.stream()
                .filter(transaction -> transaction.getAccountNumber().equals(this.accountNumber))
                .toList();

        return new ArrayList<>(transactionsByAccount);
    }

    public void displayTransactionsList() {
        List<Transaction> transactions = getTransactions();
        transactions.forEach(Transaction::displayTransactionHistory);
    }
}
