package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table
@Entity(name = "grain")
public class Grain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grain", nullable = false)
    private Long id;

    @Column(name = "weight")
    private Long weight;

    @ManyToOne
    private Recipe recipe;

    @OneToMany
    @JoinTable(
            name="GrainMalts",
            joinColumns = @JoinColumn( name="id_grain"),
            inverseJoinColumns = @JoinColumn( name="id_malt")
    )
    private Set<Malts> malt = new HashSet<>();

    public Grain() {
    }

    public Grain(Long weight, Recipe recipe, Set<Malts> malt) {
        this.weight = weight;
        this.recipe = recipe;
        this.malt = malt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Set<Malts> getMalt() {
        return malt;
    }

    public void setMalt(Set<Malts> malt) {
        this.malt = malt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grain grain = (Grain) o;

        if (id != null ? !id.equals(grain.id) : grain.id != null) return false;
        if (weight != null ? !weight.equals(grain.weight) : grain.weight != null) return false;
        if (recipe != null ? !recipe.equals(grain.recipe) : grain.recipe != null) return false;
        return malt != null ? malt.equals(grain.malt) : grain.malt == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (malt != null ? malt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grain{" +
                "id=" + id +
                ", weight=" + weight +
                ", recipe=" + recipe +
                ", malt=" + malt +
                '}';
    }
}
