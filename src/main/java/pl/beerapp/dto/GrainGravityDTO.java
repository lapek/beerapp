package pl.beerapp.dto;

import java.io.Serializable;

public class GrainGravityDTO implements Serializable {
    private Double weight;
    private Double potential;
    private Double batchSize;
    private Double efficiency;
    private Double attenuation;

    public GrainGravityDTO() {
    }

    public GrainGravityDTO(Double weight, Double potential, Double batchSize, Double efficiency, Double attenuation) {
        this.weight = weight;
        this.potential = potential;
        this.batchSize = batchSize;
        this.efficiency = efficiency;
        this.attenuation = attenuation;
    }

    public Double getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Double attenuation) {
        this.attenuation = attenuation;
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

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "GrainGravityDTO{" +
                "weight=" + weight +
                ", potential=" + potential +
                ", batchSize=" + batchSize +
                ", efficiency=" + efficiency +
                ", attenuation=" + attenuation +
                '}';
    }
}
