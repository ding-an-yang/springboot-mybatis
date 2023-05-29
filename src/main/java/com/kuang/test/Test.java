package com.kuang.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：yangan
 * @date ：2022/12/5 下午2:03
 * @description：
 * @version:
 */
public class Test {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        // 模拟业务流程
        // .......
        // 创建异步线程
        AsyncThread asyncThread = new AsyncThread();

        // 启动异步线程
        asyncThread.start();
        fun();
        System.out.println(123);
    }


    public static void fun() {
        executor.submit(new Runnable(){
            public void run() {
                try {
                    //要执行的业务代码，我们这里没有写方法，可以让线程休息几秒进行测试
                    Thread.sleep(10000);
                    System.out.print("睡够啦~");
                }catch(Exception e) {
                    throw new RuntimeException("报错啦！！");
                }
            }
        });
    }

}

class AsyncThread extends Thread{
    @Override
    public void run() {
        System.out.println("当前线程名称:" + this.getName() + ", 执行线程名称:" + Thread.currentThread().getName() + "-hello");
    }
}

