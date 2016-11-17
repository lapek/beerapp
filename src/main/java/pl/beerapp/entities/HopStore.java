package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.mapping.Collection;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "hop_store")
public class HopStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_store", nullable = false)
    private Long id;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "time")
    private Long time;

    @ManyToOne
    private Recipe recipe;

    @OneToMany
    @JoinTable(
            name="RecipeHopStore",
            joinColumns = @JoinColumn( name="id_store"),
            inverseJoinColumns = @JoinColumn( name="id_hop")
    )
    private Set<Hops> hops = new HashSet<>();

    public HopStore() {
    }

    public HopStore(Set<Hops> hops, Recipe recipe, Long time, Long weight) {
        this.hops = hops;
        this.recipe = recipe;
        this.time = time;
        this.weight = weight;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Set<Hops> getHops() {
        return hops;
    }

    public void setHops(Set<Hops> hops) {
        this.hops = hops;
    }

    @Override
    public String toString() {
        return "HopStore{" +
                "id=" + id +
                ", weight=" + weight +
                ", time=" + time +
                ", recipe=" + recipe +
                ", hops=" + hops +
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
        if (recipe != null ? !recipe.equals(hopStore.recipe) : hopStore.recipe != null) return false;
        return hops != null ? hops.equals(hopStore.hops) : hopStore.hops == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (hops != null ? hops.hashCode() : 0);
        return result;
    }
}