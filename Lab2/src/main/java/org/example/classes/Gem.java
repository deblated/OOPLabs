package org.example.classes;

import java.util.Objects;

public class Gem {
    private int id;
    private String name;
    private Preciousness preciousness;
    private String origin;
    private VisualParameters visualParameters;
    private double value;

    public Gem() {
        this.visualParameters = new VisualParameters();
    }

    public Gem(int id, String name, Preciousness preciousness, String origin, VisualParameters visualParameters, double value) {
        this.id = id;
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preciousness=" + preciousness +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Gem gem = (Gem) obj;
        return id == gem.id
                && Double.compare(gem.value, value) == 0
                && Objects.equals(name, gem.name)
                && preciousness == gem.preciousness
                && Objects.equals(origin, gem.origin)
                && Objects.equals(visualParameters, gem.visualParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, preciousness, origin, visualParameters, value);
    }
}
