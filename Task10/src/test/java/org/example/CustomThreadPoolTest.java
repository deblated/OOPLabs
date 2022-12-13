package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomThreadPoolTest {
    @Test
    void testThreadPool() {

        TestClass testClass = new TestClass();

        CustomThreadPool pool = new CustomThreadPool(4);

        pool.execute(new CustomTask("0", 30L,testClass));
        pool.execute(new CustomTask("1", 30L,testClass));
        pool.execute(new CustomTask("2", 50L,testClass));
        pool.execute(new CustomTask("3", 50L,testClass));
        pool.execute(new CustomTask("4", 70L,testClass));
        pool.execute(new CustomTask("5", 70L,testClass));

        pool.shutdown();

        pool.execute(new CustomTask("6", 70L,testClass));

        Thread waitThread = new Thread(() ->
        {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        waitThread.start();
        try {
            waitThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(6, testClass.getCounter());
    }
}

