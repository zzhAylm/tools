package com.zzh.jdk9;


public interface TestInterface {
    // before jdk 7 :
    void method1();

    // jdk 8:
    static void method2() {
        System.out.println("mehtod2");
    }

    default void method3() {
        System.out.println("mehtod3");
    }

    // jdk 9:
    private void method4() {
        System.out.println("mehtod4");
    }
}
