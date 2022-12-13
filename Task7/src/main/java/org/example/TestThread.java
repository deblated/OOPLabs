package org.example;

public class TestThread extends Thread {
    private final long duration;
    private final int number;
    private final CustomCyclicBarrier barrier;
    private final TestClass testClass;

    public TestThread(CustomCyclicBarrier barrier, long duration, int number, TestClass testClass) {
        this.barrier = barrier;
        this.duration = duration;
        this.number = number;
        this.testClass = testClass;
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<5;i++){
                Thread.sleep(duration);
                System.out.printf("Thread %d waited for %d ms %n", number, duration);
                barrier.await();
                System.out.println("Some thread action...");
                this.testClass.increment();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
