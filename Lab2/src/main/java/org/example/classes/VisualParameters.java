package org.example.classes;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VisualParameters that = (VisualParameters) obj;
        return transparency == that.transparency
                && branches == that.branches
                && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, transparency, branches);
    }
}
