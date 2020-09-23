package test;

import java.math.BigInteger;

public class JavaTest {
    public static void main(String[] args) {

        String format = String.format("%09X", 8321499137l);
        System.out.println(format);


        byte[] bytes = new BigInteger("1F0000001", 16).toByteArray();
        System.out.println(bytes);

    }
}
