package pl.beerapp.dto.RecipeDTOs;

import java.io.Serializable;

public class MashingDTO implements Serializable {
    private Double amount;
    private Double temperature;
    private Double time;

    public MashingDTO(Double amount, Double temperature, Double time) {
        this.amount = amount;
        this.temperature = temperature;
        this.time = time;
    }

    public MashingDTO() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MashingDTO{" +
                "amount=" + amount +
                ", temperature=" + temperature +
                ", time=" + time +
                '}';
    }
}
