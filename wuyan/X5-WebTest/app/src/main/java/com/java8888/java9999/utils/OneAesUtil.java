package com.java8888.java9999.utils;

public class OneAesUtil {
    public static String decrypt(String cipherText) {
        try {
            byte[] bts = new byte[cipherText.length() / 2];

            for (int i = 0; i < bts.length; ++i) {
                bts[i] = (byte) Integer.parseInt(cipherText.substring(i * 2, i * 2 + 2), 16);
            }

            System.out.println(new String(bts, "utf-8"));
            return new String(bts, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
