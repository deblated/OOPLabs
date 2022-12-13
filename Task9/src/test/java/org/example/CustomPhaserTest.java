package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class CustomPhaserTest {
    @Test
    void testPhaser() {
        final int threadsCount = 3;
        CustomPhaser phaser = new CustomPhaser();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        executor.submit(new TestThread(phaser, 50L,1));
        executor.submit(new TestThread(phaser, 75L,2));
        executor.submit(new TestThread(phaser, 100L,3));

        executor.shutdown();

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

        assertEquals(4, phaser.getPhase());
        assertEquals(0, phaser.getParties());
        assertEquals(0, phaser.getPartiesAwait());

    }
}
