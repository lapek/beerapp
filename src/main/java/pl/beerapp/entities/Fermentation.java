package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity(name = "fermentation")
public class Fermentation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ferment", nullable = false)
    private Long id_ferment;

    @Column(name = "primary_time", nullable = false)
    private Double primaryTime;

    @Column(name = "primary_temperature", nullable = false)
    private Double primaryTemperature;

    @Column(name = "secondary_time", nullable = false)
    private Double secondaryTime;

    @Column(name = "secondary_temperature", nullable = false)
    private Double secondaryTemperature;

    @OneToOne
    @JsonBackReference
    private Recipe recipe;

    @Column(name = "id_yeast")
    private Long yeastId;

//    @OneToMany
//    @JoinTable(
//            name="FermentationYeasts",
//            joinColumns = @JoinColumn( name="id_ferment"),
//            inverseJoinColumns = @JoinColumn( name="id_yeast")
//    )
//    private List<Yeast> yeasts;

//    @OneToMany
//    @JoinColumn(
//            name = "FermentationYeast",
//            joinColumns = @JoinColumn(name = "id_ferment"),
//            inverseJoinColumns = @JoinColumn(name = "id_yeast")
//    )
//    private Set<Yeast> yeasts = new HashSet<>();

    public Fermentation() {
    }

    public Fermentation(Double primaryTime, Double primaryTemperature, Double secondaryTime, Double secondaryTemperature, Recipe recipe, Long yeastId) {
        this.primaryTime = primaryTime;
        this.primaryTemperature = primaryTemperature;
        this.secondaryTime = secondaryTime;
        this.secondaryTemperature = secondaryTemperature;
        this.recipe = recipe;
        this.yeastId = yeastId;
    }

    public Long getId_ferment() {
        return id_ferment;
    }

    public void setId_ferment(Long id_ferment) {
        this.id_ferment = id_ferment;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fermentation that = (Fermentation) o;

        if (id_ferment != null ? !id_ferment.equals(that.id_ferment) : that.id_ferment != null) return false;
        if (primaryTime != null ? !primaryTime.equals(that.primaryTime) : that.primaryTime != null) return false;
        if (primaryTemperature != null ? !primaryTemperature.equals(that.primaryTemperature) : that.primaryTemperature != null)
            return false;
        if (secondaryTime != null ? !secondaryTime.equals(that.secondaryTime) : that.secondaryTime != null)
            return false;
        if (secondaryTemperature != null ? !secondaryTemperature.equals(that.secondaryTemperature) : that.secondaryTemperature != null)
            return false;
        if (recipe != null ? !recipe.equals(that.recipe) : that.recipe != null) return false;
        return yeastId != null ? yeastId.equals(that.yeastId) : that.yeastId == null;
    }

    @Override
    public int hashCode() {
        int result = id_ferment != null ? id_ferment.hashCode() : 0;
        result = 31 * result + (primaryTime != null ? primaryTime.hashCode() : 0);
        result = 31 * result + (primaryTemperature != null ? primaryTemperature.hashCode() : 0);
        result = 31 * result + (secondaryTime != null ? secondaryTime.hashCode() : 0);
        result = 31 * result + (secondaryTemperature != null ? secondaryTemperature.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (yeastId != null ? yeastId.hashCode() : 0);
        return result;
    }

    public Long getYeastId() {
        return yeastId;
    }

    public void setYeastId(Long yeastId) {
        this.yeastId = yeastId;
    }

    @Override
    public String toString() {
        return "Fermentation{" +
                "id_ferment=" + id_ferment +
                ", primaryTime=" + primaryTime +
                ", primaryTemperature=" + primaryTemperature +
                ", secondaryTime=" + secondaryTime +
                ", secondaryTemperature=" + secondaryTemperature +
                ", recipe=" + recipe +
                ", yeastId=" + yeastId +
                '}';
    }
}
