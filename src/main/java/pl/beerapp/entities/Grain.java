package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Table
@Entity(name = "grain")
public class Grain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grain", nullable = false)
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;

    @Column(name = "id_malt")
    private Long maltId;

//    @OneToMany
//    @JoinTable(
//            name="GrainMalts",
//            joinColumns = @JoinColumn( name="id_grain"),
//            inverseJoinColumns = @JoinColumn( name="id_malt")
//    )
//    private List<Malts> malts;

//    @OneToMany(cascade = {CascadeType.PERSIST})
//    @JoinTable(
//            name="GrainMalts",
//            joinColumns = @JoinColumn( name="id_grain"),
//            inverseJoinColumns = @JoinColumn( name="id_malt")
//    )
//    private Set<Malts> malt = new HashSet<>();

    public Grain() {
    }

    public Grain(Double weight, Recipe recipe, Long maltId) {
        this.weight = weight;
        this.recipe = recipe;
        this.maltId = maltId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Long getMaltId() {
        return maltId;
    }

    public void setMaltId(Long maltId) {
        this.maltId = maltId;
    }

    @Override
    public String toString() {
        return "Grain{" +
                "id=" + id +
                ", weight=" + weight +
                ", recipe=" + recipe +
                ", maltId=" + maltId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grain grain = (Grain) o;

        if (id != null ? !id.equals(grain.id) : grain.id != null) return false;
        if (weight != null ? !weight.equals(grain.weight) : grain.weight != null) return false;
        if (recipe != null ? !recipe.equals(grain.recipe) : grain.recipe != null) return false;
        return maltId != null ? maltId.equals(grain.maltId) : grain.maltId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (maltId != null ? maltId.hashCode() : 0);
        return result;
    }
}
