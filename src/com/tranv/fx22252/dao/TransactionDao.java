package com.tranv.fx22252.dao;

import com.tranv.fx22252.models.Transaction;
import com.tranv.fx22252.service.BinaryFileService;

import java.util.List;

public class TransactionDao {
    private static final String FILE_PATH = "store/transaction.dat";

    public static void save(List<Transaction> transactionList) {
        BinaryFileService.writeFile(FILE_PATH, transactionList);
    }

    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
