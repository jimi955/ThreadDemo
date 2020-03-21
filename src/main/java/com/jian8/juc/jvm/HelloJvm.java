package com.jian8.juc.jvm;

import java.util.Random;

/**
 *
 */
public class HelloJvm {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("******hello Jvm");

        Thread.sleep(Integer.MAX_VALUE);

//        String str = "jian8";
//        while (true){
//            str += str+new Random().nextInt(88888888)+new Random().nextInt(88888888);
//        }
    }
}
