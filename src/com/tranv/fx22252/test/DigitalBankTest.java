package com.tranv.fx22252.test;

import com.tranv.fx22252.exception.CustomerIdNotValidException;
import com.tranv.fx22252.models.DigitalBank;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalBankTest {
    private DigitalBank digitalBank;

    @Before

    public void setup() {
        digitalBank = new DigitalBank();

    }

    @Test
    public void showCustomers() {
        digitalBank.showCustomers();
    }

    @Test
    public void addCustomer() throws CustomerIdNotValidException {
        digitalBank.addCustomer("store/customer.txt");
    }

    @Test
    public void getCustomersById() {
    }

    @Test
    public void isCustomerExisted() {
    }

    @Test
    public void isAccountExisted() {
    }

    @Test
    public void addSavingAccount() {
    }
}