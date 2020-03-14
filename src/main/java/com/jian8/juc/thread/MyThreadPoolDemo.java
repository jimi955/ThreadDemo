package com.jian8.juc.thread;

import java.util.concurrent.*;

/**
 * 第四种获得java多线程的方式--线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(3, 5, 1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardPolicy()
//new ThreadPoolExecutor.AbortPolicy()  // 报异常
//new ThreadPoolExecutor.CallerRunsPolicy()   // 回馈主线程
new ThreadPoolExecutor.DiscardOldestPolicy() // 丢掉等待最久的，其他的继续尝试
//new ThreadPoolExecutor.DiscardPolicy() // 全部丢掉
        );
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
