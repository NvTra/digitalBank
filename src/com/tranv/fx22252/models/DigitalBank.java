package com.tranv.fx22252.models;


import com.tranv.fx22252.dao.CustomerDao;
import com.tranv.fx22252.exception.CustomerIdNotValidException;
import com.tranv.fx22252.service.TextFileService;
import com.tranv.fx22252.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank {


    private final List<Customer> customerList = CustomerDao.list();

    //Customer method
    public void showCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong danh sách");
        } else {
            customerList.forEach(Customer::displayInformation);
        }
    }

    public void addCustomer(String fileName) throws CustomerIdNotValidException {
        List<List<String>> inputData = TextFileService.readFileTxt(fileName);
        List<Customer> customers = customerList;
        int count = 0;
        if (inputData.isEmpty()) {
            System.out.println("Dữ liệu trống");
        } else {
            for (List<String> value : inputData) {
                Customer customer = new Customer(value);
                String customerId = customer.getCustomerId();
                if (!Utils.validateCustomerId(customerId)) {
                    System.out.println("Số CCCD: " + customer.getCustomerId() + " không hợp lệ!");
                } else if (isCustomerExisted(customers, customerId)) {
                    System.out.println("Khách hàng " + customer.getCustomerId() + " đã tồn tại, thêm khách hàng không thành công");
                } else {
                    System.out.println("Đã thêm khách hàng " + customerId + " vào danh sách khách hàng");
                    customers.add(customer);
                    count++;
                }
            }
        }
        if (count > 0) {
            CustomerDao.save(customers);
        }
    }

    public Customer getCustomersById(List<Customer> customersList, String customerId) {
        for (Customer c : customersList) {
            if (c.getCustomerId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    public boolean isCustomerExisted(List<Customer> customerList, String customerId) {
        return customerList.stream().anyMatch(customer -> customer.getCustomerId().equals(customerId));
    }


    //Account method
    public boolean isAccountExisted(List<Account> accountList, String accountNumber) {
        return accountList.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));
    }

    public void addSavingAccount(Scanner scanner, String customerId) {
        do {
            System.out.println(Utils.getDivider());
            System.out.print("Nhập mã số khách hàng: ");
            customerId = scanner.nextLine();
            if (!Utils.validateCustomerId(customerId) || !isCustomerExisted(CustomerDao.list(), customerId)) {
                System.out.println("Không tìm thấy khách hàng " + customerId + ", tác vụ không thành công");
            } else {
                Customer customer = getCustomersById(customerList, customerId);
                customer.input(scanner);
                break;
            }
        } while (true);

    }

    public boolean withdraw(Scanner scanner, String customerId) {
        if (!Utils.validateCustomerId(customerId)) {
            System.out.println("Mã số khách hàng không hợp lệ");
            return false;
        } else if (!isCustomerExisted(CustomerDao.list(), customerId)) {
            System.out.println("Mã số khách hàng không tồn tại");
            return false;
        } else {
            Customer customer = getCustomersById(CustomerDao.list(), customerId);
            customer.displayInformation();
            customer.withdraw(scanner);
            return true;
        }
    }

    public boolean tranfers(Scanner scanner, String customerId) {
        if (!Utils.validateCustomerId(customerId)) {
            System.out.println("Mã số khách hàng không hợp lệ");
            return false;
        } else if (!isCustomerExisted(CustomerDao.list(), customerId)) {
            System.out.println("Mã số khách hàng không tồn tại");
            return false;
        } else {
            Customer customer = getCustomersById(CustomerDao.list(), customerId);
            customer.displayInformation();
            customer.tranfers(scanner);
            return true;
        }
    }

    public boolean displayTransactionInformation(Scanner scanner, String customerId) {
        if (!Utils.validateCustomerId(customerId)) {
            System.out.println("Mã số khách hàng không hợp lệ");
            return false;
        } else if (!isCustomerExisted(CustomerDao.list(), customerId)) {
            System.out.println("Mã số khách hàng không tồn tại");
            return false;
        } else {
            getCustomersById(CustomerDao.list(), customerId).displayTransactionInformation();
            return true;
        }
    }
}
