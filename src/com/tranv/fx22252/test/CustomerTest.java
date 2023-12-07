package com.tranv.fx22252.test;

import com.tranv.fx22252.dao.AccountDao;
import com.tranv.fx22252.dao.CustomerDao;
import com.tranv.fx22252.models.Account;
import com.tranv.fx22252.models.Customer;
import com.tranv.fx22252.models.DigitalBank;
import com.tranv.fx22252.models.SavingsAccount;
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
        List<Account>accounts=AccountDao.list();
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
        Account account = new SavingsAccount("040095012040", "123678", 10000000);
        customer.addAccount(account);
        assertNotNull(CustomerDao.list());

        Account account1 = new SavingsAccount("040095012060", "111111", 10000000);
        customer.addAccount(account1);

        List<Account> account2 = AccountDao.list();
        for (Account a : account2) {
            System.out.println(a.getAccountNumber());
        }
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
    }

    @Test
    public void displayTransactionInformation() {
    }
}