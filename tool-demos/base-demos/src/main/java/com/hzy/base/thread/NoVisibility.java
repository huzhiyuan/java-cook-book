package com.hzy.thread;

public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread implements Runnable{
        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
        Thread t = new Thread(new ReaderThread());
        t.start();
        ready = true;
        number = 1;
    }
}
