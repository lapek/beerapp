package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mashing")
public class Mashing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mashing")
    private Long id_mashing;

    @Column
    private Double temperature;

    @Column(name = "time")
    private Double time;

    @Column
    private Double amount;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;

    public Mashing() {
    }

    public Mashing(Double temperature, Double time, Double amount, Recipe recipe) {
        this.temperature = temperature;
        this.time = time;
        this.amount = amount;
        this.recipe = recipe;
    }

    public Long getId_mashing() {
        return id_mashing;
    }

    public void setId_mashing(Long id_mashing) {
        this.id_mashing = id_mashing;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
