package com.jian8.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程象取写共享资源来，就不应该自由其他线程可以对资源进行读或写
 * 总结
 * - 读读能共存     （保证读的并发性）
 * - 读写不能共存   （保证数据的一致性，这也是为什么加写锁的时候，同时加读锁。因为读锁的目的就是避免写的时候，去读操作。比如，Vector，Collections.synchronizedList(new ArrayList<>()) 都是加了synchronized重锁，但是只能保证写占有，并不能保证写的时候别人不读。这样别人读的时候可能就会出错，数据不一致的现象。即使采用写时复制的CopyOnWriteArraySet等，也会出现读写不一致现象，如果想避免，加读锁即可。）
 * - 写写不能共存   （避免资源竞争，导致写入覆盖）
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, "Thread----" + i).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, "Thread" + i).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt * 2);
            }, "Thread====" + i).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * 写操作：原子+独占
     * 整个过程必须是一个完整的统一体，中间不许被分割，不许被打断
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入：" + key);
            TimeUnit.MILLISECONDS.sleep(3000);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    /**
     * 注意不加读锁的区别
     * @param key
     */
    public void get(String key) {
//        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取：" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            rwLock.readLock().unlock();
        }

    }

    public void clear() {
        map.clear();
    }
}