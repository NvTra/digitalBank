package com.tranv.fx22252.test;

import com.tranv.fx22252.exception.CustomerIdNotValidException;
import com.tranv.fx22252.models.Customer;
import com.tranv.fx22252.service.BinaryFileService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinaryFileServiceTest {
    private static String CUSTOMER_PATH = "store/customer.dat";

    @Test
    public void readFile() {
        List<Customer> customers = BinaryFileService.readFile(CUSTOMER_PATH);
        assertNotNull(customers);
    }

    @Test
    public void writeFile() throws CustomerIdNotValidException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("hoang", "040095012040"));
        customers.add(new Customer("tan", "040095012050"));
        customers.add(new Customer("nam", "040095012060"));
        customers.add(new Customer("thanh", "040095012070"));
        BinaryFileService.writeFile(CUSTOMER_PATH, customers);

        List<Customer> customers1 = BinaryFileService.readFile(CUSTOMER_PATH);
        for (int i = 0; i < customers.size(); i++) {
            assertEquals(customers.get(i).getCustomerId(), customers1.get(i).getCustomerId());
        }
    }
}