package org.example;

public class TestThread implements Runnable {
    private final long duration;
    private final CustomPhaser phaser;
    private final int threadId;

    public TestThread(CustomPhaser phaser, long duration, int threadId) {
        this.phaser = phaser;
        this.duration = duration;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        try {
            phaser.register();
            Thread.sleep(duration);
            System.out.println("Task "+Thread.currentThread().getId()+" arrived" );
            phaser.arriveAndAwaitAdvance();

            Thread.sleep(duration);
            System.out.println("Task "+Thread.currentThread().getId()+" arrived");
            phaser.arriveAndAwaitAdvance();

            Thread.sleep(duration);
            phaser.arriveAndAwaitAdvance();
            System.out.println("Task "+Thread.currentThread().getId()+" arrived");

            Thread.sleep(duration);
            System.out.println("Task "+Thread.currentThread().getId()+" finished" );
            phaser.arriveAndDeregister();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
