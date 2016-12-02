package pl.beerapp.dto;

import java.io.Serializable;

public class GrainDTO implements Serializable {
    private Double weight;
    private Double potential;
    private Double colorEBC;

    public GrainDTO() {
    }

    public GrainDTO(Double weight, Double potential, Double colorEBC) {
        this.weight = weight;
        this.potential = potential;
        this.colorEBC = colorEBC;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPotential() {
        return potential;
    }

    public void setPotential(Double potential) {
        this.potential = potential;
    }

    public Double getColorEBC() {
        return colorEBC;
    }

    public void setColorEBC(Double colorEBC) {
        this.colorEBC = colorEBC;
    }

    @Override
    public String toString() {
        return "GrainDTO{" +
                "weight=" + weight +
                ", potential=" + potential +
                ", colorEBC=" + colorEBC +
                '}';
    }
}
