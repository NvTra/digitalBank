package com.tranv.fx22252.common;


import com.tranv.fx22252.models.Account;
import com.tranv.fx22252.models.TransactionType;

public interface IReport {
    void log(double amount);

    void log(double amount, TransactionType type, Account receiveAccount);

}
