package com.jian8.juc.conditionThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        cyclicBarrierTest();
        TimeUnit.SECONDS.sleep(4);
        System.out.println("========="+Thread.currentThread().getThreadGroup().activeCount());

    }

    public static void cyclicBarrierTest() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("====召唤神龙====="+Thread.currentThread().getThreadGroup().activeCount());
        });
        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "颗龙珠:"+Thread.currentThread().getThreadGroup().activeCount());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }, "" + i).start();
        }
    }
}
