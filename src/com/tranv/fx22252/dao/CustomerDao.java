package com.tranv.fx22252.dao;

import com.tranv.fx22252.models.Customer;
import com.tranv.fx22252.service.BinaryFileService;

import java.util.List;

public class CustomerDao {
    private static final String FILE_PATH = "store/customer.dat";

    public static void save(List<Customer> customers) {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
