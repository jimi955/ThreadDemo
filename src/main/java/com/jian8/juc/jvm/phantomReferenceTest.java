package com.jian8.juc.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class phantomReferenceTest {
    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
        PhantomReference<Object> reference = new PhantomReference<Object>(o, referenceQueue);
        System.out.println(o);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());
        o=null;
        System.gc();
        System.out.println("=====");
        System.out.println(o);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());


    }
}
