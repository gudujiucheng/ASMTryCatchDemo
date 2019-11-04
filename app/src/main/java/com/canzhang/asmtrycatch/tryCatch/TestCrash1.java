package com.canzhang.asmtrycatch.tryCatch;

public class TestCrash1 {
    public static boolean crashMethod1() {
        int a = 1 / 0;
        return false;
    }

    public static void crashMethod2() {
        int a = 1 / 0;
    }


    public static boolean crashMethod3() {
        if("aa".equals(Thread.currentThread().getName())){
            return  true;
        }
        int a = 1 / 0;
        if(a>100){
            return false;
        }
        return false;
    }
}
