package com.jian8.juc.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全问题
 * ArrayList
 * 多线程对ArrayList同时修改导致数据不一致
 * 更不用说读也可能报错
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        notSafe();
        vectorTest();
//        collectionsTest();
//        copyOnWriteArrayListTest();
    }

    /**
     * 故障现象
     * java.util.ConcurrentModificationException
     *
     */
    public static void notSafe() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
                // 写的时候读（当读写同时进行 就会报错）
            }, "Thread " + i).start();
        }
    }

    /**
     * 解决方案1：使用Vector   add方法加了synchronized 重锁  并发能力下降
     */
    public static void vectorTest(){
        List<String> list = new Vector<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
    /**
     * 解决方案2
     * 使用Collections辅助类
     */
    public static void collectionsTest(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
    /**
     * 解决方案3
     * CopyOnWriteArrayList
     * 关于为什么写时复制 强烈推荐看这篇文章
     * https://www.jianshu.com/p/ceede734434b
     */
    public static void copyOnWriteArrayListTest(){
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
}
