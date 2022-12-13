package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int threadsCount = 3;
        CustomPhaser phaser = new CustomPhaser();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        executor.submit(new TestThread(phaser, 100L,1));
        executor.submit(new TestThread(phaser, 200L,2));
        executor.submit(new TestThread(phaser, 300L,3));

        executor.shutdown();
    }
}