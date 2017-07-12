package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="grain")
public class Mashing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_mashing")
    private Long id_mashing;

    @Column
    private Double temperature;

    @Column
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mashing mashing = (Mashing) o;

        if (id_mashing != null ? !id_mashing.equals(mashing.id_mashing) : mashing.id_mashing != null) return false;
        if (temperature != null ? !temperature.equals(mashing.temperature) : mashing.temperature != null) return false;
        if (time != null ? !time.equals(mashing.time) : mashing.time != null) return false;
        if (amount != null ? !amount.equals(mashing.amount) : mashing.amount != null) return false;
        return recipe != null ? recipe.equals(mashing.recipe) : mashing.recipe == null;
    }

    @Override
    public int hashCode() {
        int result = id_mashing != null ? id_mashing.hashCode() : 0;
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mashing{" +
                "id_mashing=" + id_mashing +
                ", temperature=" + temperature +
                ", time=" + time +
                ", amount=" + amount +
                ", recipe=" + recipe +
                '}';
    }
}
