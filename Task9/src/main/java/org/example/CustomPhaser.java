package org.example;

public class CustomPhaser {
    private int parties;
    private int phase;
    private int partiesAwait;

    public CustomPhaser() {
        this(0);
    }

    public CustomPhaser(int parties) {
        this.parties = parties;
        this.phase = 0;
        this.partiesAwait = parties;
    }

    public int getPhase() {
        return phase;
    }

    public int getParties() {
        return parties;
    }

    public int getPartiesAwait() {
        return partiesAwait;
    }

    public synchronized void register() {
        parties++;
        partiesAwait++;
    }

    public synchronized void arriveAndAwaitAdvance() throws InterruptedException {
        partiesAwait--;

        if (partiesAwait > 0) {
            wait();
        }
        else{
            notifyAll();
            partiesAwait = parties;
            phase++;
        }
    }

    public synchronized void arriveAndDeregister() {
        partiesAwait--;
        parties--;

        if (partiesAwait == 0) {
            notifyAll();
            phase++;
            partiesAwait = parties;
        }

    }
}
