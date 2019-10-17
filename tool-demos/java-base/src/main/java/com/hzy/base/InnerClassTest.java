package com.hzy.base;

/**
 * 私有静态内部类,实现了公开接口
 */
public class InnerClassTest {
    public static void main(String[] args) {
        Inner inner = Outer.getInner();
        System.out.println(inner.getName());
    }
}

interface Inner {
    String getName();
}
class Outer{

    public static Inner1 getInner(){
        return new Inner1("test");
    }
    private static class Inner1 implements Inner{
        private String name;
        public Inner1(String name){
            this.name=name;
        }
        public String getName(){
            return this.name;
        };
    }
}
