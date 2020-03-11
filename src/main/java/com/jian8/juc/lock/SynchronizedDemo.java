package com.jian8.juc.lock;

/**
 * synchronized 可重入锁 获得外面的锁的同时获取内部相同的锁  但是锁的数量要匹配  不能是奇数
 *
 * 可以防止死锁的出现
 * （主要时执行的时候，获取资源的顺序相同，先执行方法一在执行方法二 如果方法一被某一个线程执行，别的线程就得不到方法一的执行权限 因此不会常出现死锁）
 */


public class SynchronizedDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 1").start();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 2").start();
    }
}
class Phone{
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t -----invoked sendSMS()");
        Thread.sleep(3000);
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t +++++invoked sendEmail()");
    }
}