package pl.beerapp.dto.RecipeDTOs;

import java.io.Serializable;

public class GrainDTO implements Serializable {
    private Long maltID;
    private Double weight;

    public GrainDTO() {
    }

    public GrainDTO(Long maltID, Double weight) {
        this.maltID = maltID;
        this.weight = weight;
    }

    public Long getMaltID() {
        return maltID;
    }

    public void setMaltID(Long maltID) {
        this.maltID = maltID;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "GrainDTO{" +
                "maltID=" + maltID +
                ", weight=" + weight +
                '}';
    }
}
