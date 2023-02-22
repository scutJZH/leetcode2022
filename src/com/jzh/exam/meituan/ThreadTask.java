package com.jzh.exam.meituan;

public class ThreadTask {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread thread1 = new MyThread(lock);
        MyThread2 thread2 = new MyThread2(lock);

        thread1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    public static volatile int i = 0;

    public static class MyThread extends Thread {
        public final Object lock;

        public MyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println("MyThread1 get the lock");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class MyThread2 extends Thread {
        public final Object lock;

        public MyThread2(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("MyThread2 get the lock");
            }
        }
    }
}
