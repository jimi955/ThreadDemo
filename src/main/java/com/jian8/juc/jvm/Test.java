package com.jian8.juc.jvm;

import java.util.Random;

/**
 * 虚拟机的设置
 * -XX:+PrintGCDetails
 * -XX:MetaspaceSize=128m
 * -XX:MaxTenuringThreshold=15
 *
 * 经典设置：-Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 *
 * -XX:SurvivorRatio=8      Eden:S0:S1=8:1:1
 * -XX:NewRatio=2           新生代:老年代=1:2
 *
 */
public class Test {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());//线程数
        System.out.println(runtime.totalMemory()/(double)1024/1024);//初始内存-Xms
        System.out.println(runtime.maxMemory()/(double)1024/1024);//最大内存-Xmx
        String str = "jian8";
        while (true){
            str += str+new Random().nextInt(88888888)+new Random().nextInt(88888888);
        }
    }
}
