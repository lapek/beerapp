package pl.beerapp.dto;

import java.io.Serializable;

public class GrainColorDTO implements Serializable {
    private Double grain;
    private Double colorEBC;
    private Double batchSize;

    public GrainColorDTO() {
    }

    public GrainColorDTO(Double grain, Double colorEBC, Double batchSize) {
        this.grain = grain;
        this.colorEBC = colorEBC;
        this.batchSize = batchSize;
    }

    public Double getGrain() {
        return grain;
    }

    public void setGrain(Double grain) {
        this.grain = grain;
    }

    public Double getColorEBC() {
        return colorEBC;
    }

    public void setColorEBC(Double colorEBC) {
        this.colorEBC = colorEBC;
    }

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public String toString() {
        return "GrainColorDTO{" +
                "grain=" + grain +
                ", colorEBC=" + colorEBC +
                ", batchSize=" + batchSize +
                '}';
    }
}
