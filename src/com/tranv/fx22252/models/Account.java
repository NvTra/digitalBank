package com.tranv.fx22252.models;

import com.tranv.fx22252.dao.CustomerDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 2L;
    private final String accountNumber;
    private double balance;
    private String accountType;
    private String customerId;
    private final List<Transaction> transactionList = new ArrayList<>();

    public Account(String customerId, String accountNumber, double balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;

    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isPremiumAccount() {
        return this.balance > 10000000;
    }

    public Customer getCustomer() {
        List<Customer> customerList = CustomerDao.list();
        return customerList.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }



    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void createTranSaction(double amount, boolean status, TransactionType type) {
        transactionList.add(new Transaction(accountNumber, amount, true, type));
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
