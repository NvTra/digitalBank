package com.tranv.fx22252.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {
    public static String getDivider() {
        return "+----------+---------------------+---------+";
    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String formatBalance(double balance) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(balance) + " đ";
    }

    public static boolean validateAccountNumber(String myStr) {
        return myStr.length() == 6 && myStr.chars().anyMatch(Character::isDigit);

    }
    public static boolean validateCustomerId(String customerId) {
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
