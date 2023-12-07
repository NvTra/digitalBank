package com.tranv.fx22252.dao;

import com.tranv.fx22252.models.Account;
import com.tranv.fx22252.service.BinaryFileService;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static final String FILE_PATH = "store/account.dat";

    public static void save(List<Account> accounts) {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public void update(Account editAcount) {
        List<Account> accounts = this.list();
        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAcount.getAccountNumber()));
        List<Account> updateAccounts;
        if (!hasExist) {
            updateAccounts = new ArrayList<>();
            updateAccounts.add(editAcount);
        } else {
            updateAccounts = new ArrayList<>();
            for (Account a : accounts) {
                if (a.getAccountNumber().equals(editAcount.getAccountNumber())) {
                    updateAccounts.add(editAcount);
                } else {
                    updateAccounts.add(a);
                }
            }
        }
    }
}
