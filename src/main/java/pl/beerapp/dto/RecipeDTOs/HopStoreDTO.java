package pl.beerapp.dto.RecipeDTOs;

import java.io.Serializable;

public class HopStoreDTO implements Serializable {
    private Long hopID;
    private Double weight;
    private Double time;

    public HopStoreDTO() {
    }

    public HopStoreDTO(Long hopID, Double weight, Double time) {
        this.hopID = hopID;
        this.weight = weight;
        this.time = time;
    }

    public Long getHopID() {
        return hopID;
    }

    public void setHopID(Long hopID) {
        this.hopID = hopID;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HopStoreDTO{" +
                "hopID=" + hopID +
                ", weight=" + weight +
                ", time=" + time +
                '}';
    }
}
