package pl.beerapp.dto.RecipeDTOs;

import java.io.Serializable;

public class FermentationDTO implements Serializable {
    private Double primaryTime;
    private Double primaryTemperature;
    private Double secondaryTime;
    private Double secondaryTemperature;

    public FermentationDTO() {
    }

    public FermentationDTO(Double primaryTime, Double primaryTemperature, Double secondaryTime, Double secondaryTemperature) {
        this.primaryTime = primaryTime;
        this.primaryTemperature = primaryTemperature;
        this.secondaryTime = secondaryTime;
        this.secondaryTemperature = secondaryTemperature;
    }

    public Double getPrimaryTime() {
        return primaryTime;
    }

    public void setPrimaryTime(Double primaryTime) {
        this.primaryTime = primaryTime;
    }

    public Double getPrimaryTemperature() {
        return primaryTemperature;
    }

    public void setPrimaryTemperature(Double primaryTemperature) {
        this.primaryTemperature = primaryTemperature;
    }

    public Double getSecondaryTime() {
        return secondaryTime;
    }

    public void setSecondaryTime(Double secondaryTime) {
        this.secondaryTime = secondaryTime;
    }

    public Double getSecondaryTemperature() {
        return secondaryTemperature;
    }

    public void setSecondaryTemperature(Double secondaryTemperature) {
        this.secondaryTemperature = secondaryTemperature;
    }

    @Override
    public String toString() {
        return "FermentationDTO{" +
                "primaryTime=" + primaryTime +
                ", primaryTemperature=" + primaryTemperature +
                ", secondaryTime=" + secondaryTime +
                ", secondaryTemperature=" + secondaryTemperature +
                '}';
    }
}
