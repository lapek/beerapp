package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="fermentation")
public class Fermentation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_ferment")
    private Long idFerment;

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
//            joinColumns = @JoinColumn( name="idFerment"),
//            inverseJoinColumns = @JoinColumn( name="id_yeast")
//    )
//    private List<Yeast> yeasts;


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

    public Long getIdFerment() {
        return idFerment;
    }

    public void setIdFerment(Long idFerment) {
        this.idFerment = idFerment;
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

    public Long getYeastId() {
        return yeastId;
    }

    public void setYeastId(Long yeastId) {
        this.yeastId = yeastId;
    }
}
