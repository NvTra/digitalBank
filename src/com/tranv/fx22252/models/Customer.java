package com.tranv.fx22252.models;


import com.tranv.fx22252.dao.AccountDao;
import com.tranv.fx22252.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Customer extends User implements Serializable {
    private final long serialVersionUID = 2L;
    private final List<Account> accountList = AccountDao.list();

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
        List<Account> filterList = AccountDao.list().stream()
                .filter(account -> account.getCustomerId().equals(this.getCustomerId()))
                .toList();
        return new ArrayList<>(filterList);
    }

    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    public boolean isAccountExist(String accountNumber) {
        return accountList.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));
    }

    public void addAccount(Account account) {
        List<Account> accountList = AccountDao.list();
        for (Account a : accountList) {
            if (a.getAccountNumber().equals(account.getAccountNumber())) {
                System.out.println("Tài khoản đã được sử dụng");
                break;
            }
        }

        accountList.add(account);
        AccountDao.save(accountList);
    }


    public void input(Scanner scanner) {
        String accountNumber;
        do {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            accountNumber = scanner.nextLine();
        } while (!Utils.validateAccountNumber(accountNumber) || isAccountExist(accountNumber));
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
        System.out.println("thêm tài khoản thành công");
    }

    public boolean isCustomerPremium() {
        for (Account a : getAccounts()) {
            if (a.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }

    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account a : getAccounts()) {
            totalBalance += a.getBalance();
        }
        return totalBalance;
    }

    public void displayInformation() {
        System.out.printf("%-14s |%20s | %8s | %16s", this.getCustomerId()
                , this.getName()
                , this.isCustomerPremium() ? "PREMIUM" : "Normal"
                , Utils.formatBalance(getTotalBalance()));

        if (!getAccounts().isEmpty()) {
            for (int i = 0; i < getAccounts().size(); i++) {
                System.out.println();
                System.out.printf("%-2s%12s |%50s", i + 1
                        , getAccounts().get(i).getAccountNumber()
                        , Utils.formatBalance(getAccounts().get(i).getBalance()));
            }
        }
        System.out.println();
    }

    public void withdraw(Scanner scanner) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account account;
            double amount;

            do {
                System.out.print("Nhập số tài khoản: ");
                account = getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (account == null);
            do {
                System.out.print("Nhập số tiền rút: ");
                amount = Double.parseDouble(scanner.nextLine());
            } while (amount <= 0);

            ((SavingsAccount) account).withdraw(amount);
            AccountDao.update(account);

        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }

    public void tranfers(Scanner scanner) {
        List<Account> accounts = getAccounts();
        List<Account> accountList1 = AccountDao.list();
        Account sendAccount;
        Account receiveAccount;
        double amount;
        String confirm;
        do {
            System.out.print("Nhập số tài khoản: ");
            sendAccount = getAccountByAccountNumber(accounts, scanner.nextLine());
        } while (sendAccount == null);
        do {
            System.out.print("Nhập số tài khoản nhận(exit để thoát): ");
            receiveAccount = getAccountByAccountNumber(accountList1, scanner.nextLine());
        } while (receiveAccount == null);
        System.out.println("Gửi tiền đến tài khoản: " + receiveAccount.getAccountNumber() + " | " + receiveAccount.getCustomer().getName());
        do {
            System.out.print("Nhập số tiền chuyển: ");
            amount = Double.parseDouble(scanner.nextLine());
        } while (amount <= 0);

        do {
            System.out.print("Xác nhận thực hiện chuyển: " + Utils.formatBalance(amount) +
                    " từ tài khoản: [" + sendAccount.getAccountNumber() +
                    "] đến tài khoản [" + receiveAccount.getAccountNumber() + "] (Y/N):");
            confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                ((SavingsAccount) sendAccount).tranfer(receiveAccount, amount);
                AccountDao.update(receiveAccount);
                AccountDao.update(sendAccount);
                break;
            }
        } while (!confirm.equalsIgnoreCase("n"));
    }

    public void displayTransactionInformation() {
        displayInformation();

    }
}
