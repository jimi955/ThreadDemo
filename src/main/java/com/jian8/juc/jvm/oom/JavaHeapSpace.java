package com.jian8.juc.jvm.oom;

import java.util.Random;

/**
 * -Xms1m -Xmx1m
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class JavaHeapSpace {

    public static void main(String[] args) {
        String a="aa";
        while (true) {
            // 字符串 + 操作 属于不断的创建字符串变量存到变量池中  撑破堆内存
            a+= new Random().nextInt(88888888)+new Random().nextInt(111111111);
            System.out.println(a.intern());
        }
    }
}
