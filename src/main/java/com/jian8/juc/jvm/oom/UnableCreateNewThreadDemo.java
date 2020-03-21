package com.jian8.juc.jvm.oom;

/**
 * -Xms1m -Xmx1m
 * GC执行的时间占98% 而且只回收不到2%的堆内存
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 *
 *
 * -Xms10m -Xmx10m
 * linux环境下 单个进程会只能产生1024个线程 超过线程数会报错 window不会报错
 * Unable to Create New native Thread
 *
 * window环境下会消耗堆内存 导致OOM (Java heap space)
 * Exception in thread "main" Exception in thread "14217" java.lang.OutOfMemoryError: Java heap space
 *
 *
 *
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        Integer num = 0;
        while (true) {
            num += 1;

            new Thread(() -> {
                try {
                    System.out.println("创建线程：" + Thread.currentThread().getName());
                    Thread.sleep(300000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },num.toString()).start();
        }
    }
}
