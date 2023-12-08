package com.tranv.fx22252.models;

import com.tranv.fx22252.common.IReport;

import com.tranv.fx22252.common.ITranfer;
import com.tranv.fx22252.common.IWithdraw;
import com.tranv.fx22252.utils.Utils;

import java.io.Serializable;

public class SavingsAccount extends Account implements IWithdraw, IReport, ITranfer, Serializable {
    private final long serialVersionUID = 2L;
    private final String SAVINGS_ACCOUNT_TYPE = "SAVINGS";


    public SavingsAccount(String customerId, String accountNumber, double balance) {
        super(customerId, accountNumber, balance);
        this.setAccountType(SAVINGS_ACCOUNT_TYPE);
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", "BIÊN LAI GIAO DỊCH " + SAVINGS_ACCOUNT_TYPE);
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SỐ TK: %31s%n", getAccountNumber());
        System.out.printf("SỐ TIỀN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHÍ + VAT: %27s%n", "0 đ");
        System.out.println(Utils.getDivider());
    }

    @Override
    public void log(double amount, TransactionType type, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", "BIÊN LAI GIAO DỊCH " + SAVINGS_ACCOUNT_TYPE);
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SỐ TK: %31s%n", getAccountNumber());
        System.out.printf("SỐ TK NHẬN: %26s%n", receiveAccount.getAccountNumber());
        System.out.printf("SỐ TIỀN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHÍ + VAT: %27s%n", "0 đ");
        System.out.println(Utils.getDivider());
    }


    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            createTransaction(amount, true, TransactionType.WITHDRAW);
            System.out.println("Rút tiền thành công, biên lai giao dịch: ");
            log(amount);
            return true;
        }
        System.out.println("G/D that bai");
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount < 50000) {
            System.out.println("Số tiền rút phải lớn hơn 50,000 đ");
            return false;
        }
        if (!this.isPremiumAccount()) {
            if (amount > 5000000) {
                System.out.println("Tài khoản bị giới hạn rút tối đa 5,000,000 đ");
                return false;
            }
        }
        if (this.getBalance() - amount < 50000) {
            System.out.println("Số dư tối thiểu còn lại phải lớn hơn 50,000 đ");
            return false;
        }
        if (amount % 10000 != 0) {
            System.out.println("Số tiền rút phải là bội số của 10,000 đ");
            return false;
        }
        return true;
    }


    @Override
    public void tranfer(Account receiveAccount, double amount) {
        if (isAccepted(amount)) {
            createTransaction(amount, true, TransactionType.TRANSFER);
            receiveAccount.createTransaction(amount, true, TransactionType.DEPOSIT);
            System.out.println("Chuyển tiền thành công, biên lai giao dịch: ");
            log(amount,TransactionType.TRANSFER,receiveAccount);
        } else {
            System.out.println("giao dịch không thành công");
        }
    }
}
