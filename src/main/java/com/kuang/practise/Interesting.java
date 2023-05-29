package com.kuang.practise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author ：yangan
 * @date ：2023/3/1 下午4:45
 * @description：
 * @version:
 */
@Slf4j
public class Interesting {
    volatile int a = 1;
    volatile int b = 1;

    public synchronized void add() {
        log.info("add start");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        log.info("add done");
    }

    public synchronized void compare() {
        log.info("compare start");
        for (int i = 0; i < 10000; i++) { //a始终等于b吗？
            if (a < b) {
                System.out.println("a:" + a + "    b:" + b + "    a < b:" + (a < b));
                //最后的a>b应该始终是false吗？
            }
        }
        log.info("compare done");
    }

    public static void main(String[] args) {
//        Interesting interesting = new Interesting();
//        new Thread(interesting::add).start();
//        new Thread(interesting::compare).start();
        Data.reset();
        //多线程循环一定次数调用Data类不同实例的wrong方法
        IntStream.rangeClosed(1, 1000).parallel().forEach(i -> new Data().wrong());
        System.out.println(Data.getCounter());
    }
}

@Slf4j
class Data {
    @Getter
    private static int counter = 0;
    private static final Object locker = new Object();

    public static void reset() {
        counter = 0;
    }

    public void wrong() {
        synchronized(locker){
            counter++;
        }
        log.info("当前线程" + "\t" + counter);
    }
}
