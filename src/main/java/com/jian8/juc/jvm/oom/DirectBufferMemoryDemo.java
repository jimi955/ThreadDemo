package com.jian8.juc.jvm.oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * MaxDirectMemorySize：设置堆外内存的大小  参考 https://blog.csdn.net/ZYC88888/article/details/80228531
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 *
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"+sun.misc.VM.maxDirectMemory()/(double)1024/1024);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 使用堆外内存 maxDirectMemory 6m
        ByteBuffer.allocateDirect(6*1024*1024);
    }
}
