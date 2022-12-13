package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomCyclicBarrierTest {
    @Test
    void testThreadPool() throws InterruptedException {

        TestClass testClass = new TestClass();
        int threadsCount = 4;
        Thread[] threads = new Thread[threadsCount];
        CustomCyclicBarrier barrier = new CustomCyclicBarrier(threadsCount, () -> System.out.println("Finish"));
        long minDuration = 50L;

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TestThread(barrier, (i + 1) * minDuration, i,testClass);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(20, testClass.getCounter());
    }
}


