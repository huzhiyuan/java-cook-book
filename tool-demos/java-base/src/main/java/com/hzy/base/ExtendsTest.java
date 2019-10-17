package com.hzy.base;

import java.util.HashMap;
import java.util.Map;

public class ExtendsTest {
    public static void main(String[] args) {
        Test test = new Test();
    }
}
class Base{
    private static String x = Log.print("base");
    static {
        System.out.println("base static block");
    }
    {
        System.out.println("base block");
    }
    private String y;
    public Base(){
        System.out.println("base structure");
    }

}
class Test extends Base{
    static {
        System.out.println("Test static block");
    }
    private String y =Log.print("y");
    {
        System.out.println("Test block");
    }
    private static String x = Log.print("test");
    public Test(){
        System.out.println("test structure");
    }

    public void test1(HashMap map){
        System.out.println("test");
    }

    public void test1(Map map){
        System.out.println("test");
    }

}
class Log{
    public static String print(String message){
        System.out.println("log "+message);
        return null;
    }
}