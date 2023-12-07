package com.tranv.fx22252.models;

import com.tranv.fx22252.dao.AccountDao;
import com.tranv.fx22252.utils.Utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    private final long serialVersionUID = 2L;
    private final List<Account> accounts = AccountDao.list();

    public Customer() {
        super();
    }

    public Customer(String name, String customerId) {
        super(name, customerId);
    }


    public Customer(List<String> customer) {
        this(customer.get(1), customer.get(0));
    }

    //Account method
    public List<Account> getAccounts() {
        List<Account> accountList = AccountDao.list();
        List<Account> filterList = accountList.stream()
                .filter(account -> account.getCustomerId().equals(this.getCustomerId()))
                .collect(Collectors.toList());
        return new ArrayList<>(filterList);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return getAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    public void addAccount(Account account) {
        List<Account> accountList = getAccounts();
        for (Account a : accountList) {
            if (a.getAccountNumber().equals(account.getAccountNumber())) {
                break;
            }
        }

        getAccounts().add(account);
        AccountDao.save(getAccounts());
        System.out.println("thêm tài khoản thành công");
    }

    public void input(Scanner scanner) {
        String accountNumber;
        do {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            accountNumber = scanner.nextLine();
        } while (Utils.validateAccountNumber(accountNumber));
        double balance = 0;
        do {
            try {
                System.out.print("Nhập số dư tài khoản >=50000: ");
                balance = scanner.nextDouble();
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (balance < 50000);
        addAccount(new SavingsAccount(getCustomerId(), accountNumber, balance));
    }

    public boolean isCustomerPremium() {
        for (Account a : accounts) {
            if (a.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }


    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account a : accounts) {
            totalBalance += a.getBalance();
        }
        return totalBalance;
    }

    public void displayInformation() {
        DecimalFormat df = new DecimalFormat("#,### đ");
        System.out.printf("%-14s |%20s | %8s | %15s", this.getCustomerId()
                , this.getName()
                , this.isCustomerPremium() ? "PREMIUM" : "Normal"
                , df.format(getTotalBalance()));
        if (!getAccounts().isEmpty()) {
            for (int i = 0; i < getAccounts().size(); i++) {
                System.out.println();
                System.out.printf("%-2s%12s |%49s", i + 1
                        , getAccounts().get(i).getAccountNumber()
                        , df.format(getAccounts().get(i).getBalance()));
            }
        }
        System.out.println();
    }

    public void displayTransactionInformation() {
        displayInformation();

    }


}
