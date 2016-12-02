package pl.beerapp.dto;

import java.io.Serializable;

public class HopsDTO implements Serializable {
    private Double weight;
    private Double time;
    private Double alpha;

    public HopsDTO() {
    }

    public HopsDTO(Double weight, Double time, Double alpha) {
        this.weight = weight;
        this.time = time;
        this.alpha = alpha;
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

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "HopsDTO{" +
                "weight=" + weight +
                ", time=" + time +
                ", alpha=" + alpha +
                '}';
    }
}
