package com.tranv.fx22252.models;

import java.util.UUID;

public class Bank {

    private final String id;
    private final String bankName = "ACTIVEBANK";
    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
    }

}
