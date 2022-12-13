package org.example;

public class TestClass {
    private int counter;

    public TestClass() {
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public synchronized void increment() {
        counter++;
    }

}