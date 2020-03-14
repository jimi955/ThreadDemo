package com.jian8.juc.conditionThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        general();
        countDownLatchTest();
    }

    public static void general(){
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
            }, "Thread-->"+i).start();
        }
        while (Thread.activeCount()>2){
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+"\t=====班长最后关门走人");
    }

    /**
     * CountDownLatch计数器  初始化带入值 配合countDownLatch.countDown();使用
     * countDownLatch.countDown();  被守护的线程  （不阻塞）
     * countDownLatch.await();  需要等待的线程用   （阻塞）
     * @throws InterruptedException
     */
    public static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6); // 计数器
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
//        TimeUnit.SECONDS.sleep(4);
//        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t=====秦统一");
    }
}
