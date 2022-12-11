package org.example;

import java.util.Objects;

public class VisualParameters {
    private String color;
    private int transparency;
    private int branches;

    public VisualParameters() {
    }

    public VisualParameters(String color, int transparency, int branches) {
        this.color = color;
        this.transparency = transparency;
        this.branches = branches;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getBranches() {
        return branches;
    }

    public void setBranches(int branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "color='" + color + '\'' +
                ", transparency=" + transparency +
                ", branches=" + branches +
                '}';
    }
}
