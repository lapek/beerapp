package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "hop_store")
public class HopStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_store")
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "time")
    private Double time;

    @Column(name = "id_hop")
    private Long hopId;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;


//    @OneToMany
//    @JoinTable(
//            name="HopStoreHops",
//            joinColumns = @JoinColumn( name="id_store"),
//            inverseJoinColumns = @JoinColumn( name="id_hop")
//    )
//    private List<Hop> hops;

    public HopStore() {
    }

    public HopStore(Double weight, Double time, Long hopId, Recipe recipe) {
        this.weight = weight;
        this.time = time;
        this.hopId = hopId;
        this.recipe = recipe;
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

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Long getHopId() {
        return hopId;
    }

    public void setHopId(Long hopId) {
        this.hopId = hopId;
    }

    @Override
    public String toString() {
        return "HopStore{" +
                "id=" + id +
                ", weight=" + weight +
                ", time=" + time +
                ", hopId=" + hopId +
                ", recipe=" + recipe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HopStore hopStore = (HopStore) o;

        if (id != null ? !id.equals(hopStore.id) : hopStore.id != null) return false;
        if (weight != null ? !weight.equals(hopStore.weight) : hopStore.weight != null) return false;
        if (time != null ? !time.equals(hopStore.time) : hopStore.time != null) return false;
        if (hopId != null ? !hopId.equals(hopStore.hopId) : hopStore.hopId != null) return false;
        return recipe != null ? recipe.equals(hopStore.recipe) : hopStore.recipe == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (hopId != null ? hopId.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        return result;
    }
}