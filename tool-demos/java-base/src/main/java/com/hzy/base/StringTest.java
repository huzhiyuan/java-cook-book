package com.hzy.base;

public class StringTest {
    public static void main(String[] args) {
        String a = "ab";
        String b = "a"+"b";
        String c = new String("ab");
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(b==c);
        System.out.println("ab"==("a"+"b"));
        System.out.println(a.equals(c));
    }
}
