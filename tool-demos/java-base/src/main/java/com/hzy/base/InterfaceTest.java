package com.hzy.base;

public class InterfaceTest implements  InterfaceX{
    @Override
    public void test() {
    }

    @Override
    public void test2() {
    }

    @Override
    public void test1() {
    }
}

interface InterfaceX extends Interface1,Interface2{

}
interface Interface1{
    public void test();
    public void test1();
}

interface Interface2{
    public void test2();
    public void test();
    //public String test();
}