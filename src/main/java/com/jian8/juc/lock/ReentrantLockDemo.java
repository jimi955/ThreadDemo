package com.jian8.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock 可重入锁 获得外面的锁的同时获取内部相同的锁  但是锁的数量要匹配  不能是奇数
 *
 * 可以防止死锁的出现
 * （主要时执行的时候，获取资源的顺序相同，先执行方法一在执行方法二 如果方法一被某一个线程执行，别的线程就得不到方法一的执行权限 因此不会常出现死锁）
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        new Thread(mobile).start();
        new Thread(mobile).start();
    }
}
class Mobile implements Runnable{
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        }finally {
            lock.unlock();
        }
    }
}
