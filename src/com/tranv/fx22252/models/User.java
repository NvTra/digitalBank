package com.tranv.fx22252.models;

import java.io.Serializable;

public class User implements Serializable {
    private final long serialVersionUID = 2L;
    private String name;
    private String customerId;

    public User() {
    }

    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) throws Exception {
        if (checkCCCD(customerId)) {
            this.customerId = customerId;
        } else {
            throw new Exception("Số CCCD không hợp lệ");
        }
    }

    public static boolean checkCCCD(String customerId) {
        int[] maTinh = {1, 2, 4, 6, 8, 10, 11, 12, 14, 15, 17, 19, 20, 22, 24, 25, 26, 27, 30, 31, 33, 34, 35, 36, 37, 38, 40, 42, 44, 45, 46, 48, 49, 51, 52, 54, 56, 58, 60, 62, 64, 66, 67, 68, 70, 72, 74, 75, 77, 79, 80, 82, 83, 84, 86, 87, 89, 91, 92, 93, 94, 95, 96
        };
        boolean flag = false;
        if (customerId.length() != 12) {
            return false;
        }
        if (!customerId.chars().allMatch(Character::isDigit)) {
            return false;
        }
        for (int n : maTinh) {
            if (Integer.parseInt(customerId.substring(0, 3)) == n) {
                flag = true;
            }
        }
        return flag;
    }
}
