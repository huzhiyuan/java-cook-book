package com.hzy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int number = Integer.valueOf(args[0]);
        int sum = 0;
        for (int i = 0; i < number; i++) {
            sum = sum + i;
        }
        System.out.println(sum);
    }
}
