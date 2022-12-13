package org.example.transport;

public abstract class Transport {
    public Transport(String model) {
        this.model = model;
    }

    protected final String model;

    public String getModel() {
        return model;
    }
}
