package com.jian8.juc.jvm.oom;

/**
 * StackOverflowError  耗尽 线程虚拟机栈内存
 */
public class StackOverFlowError {
    public static void main(String[] args) {
        stackOverflow();

    }

    private static void stackOverflow() {
        // 递归
        stackOverflow();
    }
}
