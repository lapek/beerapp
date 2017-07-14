package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "grain")
public class Grain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_grain")
    private Long idGrain;

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
//    private List<Malt> malts;

//    @OneToMany(cascade = {CascadeType.PERSIST})
//    @JoinTable(
//            name="GrainMalts",
//            joinColumns = @JoinColumn( name="id_grain"),
//            inverseJoinColumns = @JoinColumn( name="id_malt")
//    )
//    private Set<Malt> malt = new HashSet<>();

    public Grain() {
    }

    public Grain(Double weight, Recipe recipe, Long maltId) {
        this.weight = weight;
        this.recipe = recipe;
        this.maltId = maltId;
    }

    public Long getIdGrain() {
        return idGrain;
    }

    public void setIdGrain(Long idGrain) {
        this.idGrain = idGrain;
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
                "idGrain=" + idGrain +
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

        if (idGrain != null ? !idGrain.equals(grain.idGrain) : grain.idGrain != null) return false;
        if (weight != null ? !weight.equals(grain.weight) : grain.weight != null) return false;
        if (recipe != null ? !recipe.equals(grain.recipe) : grain.recipe != null) return false;
        return maltId != null ? maltId.equals(grain.maltId) : grain.maltId == null;
    }

    @Override
    public int hashCode() {
        int result = idGrain != null ? idGrain.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (maltId != null ? maltId.hashCode() : 0);
        return result;
    }
}
