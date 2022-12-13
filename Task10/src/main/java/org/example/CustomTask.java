package org.example;

public class CustomTask implements Runnable {
    private final String name;
    private final long duration;
    private TestClass testClass;

    public CustomTask(String name, long duration, TestClass testClass) {
        this.name = name;
        this.duration = duration;
        this.testClass=testClass;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            testClass.increment();
            System.out.println("Thread: " + name + " finished its task");
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }
}
