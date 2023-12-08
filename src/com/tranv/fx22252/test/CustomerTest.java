package com.tranv.fx22252.test;

import com.tranv.fx22252.dao.AccountDao;
import com.tranv.fx22252.dao.CustomerDao;
import com.tranv.fx22252.dao.TransactionDao;
import com.tranv.fx22252.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CustomerTest {
    private Customer customer;
    DigitalBank digitalBank;

    @Before
    public void setup() {
        digitalBank = new DigitalBank();
//        digitalBank.addCustomer("store/customer.txt");

        customer = digitalBank.getCustomersById(CustomerDao.list(), "040095012040");
        List<Account> accounts = customer.getAccounts();
    }

    @Test
    public void getAccountList() {
        List<Account> accounts = AccountDao.list();
        assertNotNull(accounts);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void getAccountByAccountNumber() {
    }

    @Test
    public void addAccount() {
        Account account = new SavingsAccount("040095012040", "111111", 10000000);
        customer.addAccount(account);
        assertNotNull(CustomerDao.list());

        Account account1 = new SavingsAccount("040095012060", "222222", 10000000);
        customer.addAccount(account1);
        account.createTransaction(0, true, TransactionType.DEPOSIT);
        List<Account> account2 = AccountDao.list();
        for (Account a : account2) {
            System.out.println(a.getAccountNumber());
        }
        account1.createTransaction(0, true, TransactionType.DEPOSIT);
        digitalBank.showCustomers();
    }

    @Test
    public void isAccountExist() {
    }

    @Test
    public void input() {
    }

    @Test
    public void isCustomerPremium() {
    }

    @Test
    public void getTotalBalance() {
    }

    @Test
    public void displayInformation() {
        TransactionDao.list().forEach(Transaction::displayTransactionHistory);
    }

    @Test
    public void displayTransactionInformation() {
        customer.displayTransactionInformation();
    }
}