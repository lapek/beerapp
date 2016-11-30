package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe", nullable = false)
    private Long id_recipe;

    @Column(name = "style")
    private String style;

    @Column(name = "name")
    private String name;

    @Column(name = "efficiency", nullable = false)
    private Double efficiency;

    @Column(name = "volume", nullable = false)
    private Double volume;

    @Column(name = "boil_time", nullable = false)
    private Double boilTime;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private AppUser appUser;

    @OneToMany
    @JoinTable(
            name = "RecipeGrain",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_grain")
    )
    private Set<Grain> grains = new HashSet<>();

    @OneToMany
    @JoinTable(
            name = "RecipeHopStore",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_store")
    )
    private Set<HopStore> hopStore = new HashSet<>();

    @OneToMany
    @JoinTable(
            name = "RecipeYeast",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_yeast")
    )
    private Set<Yeast> yeast = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String style, String name, Double efficiency, Double volume, Double boilTime, boolean visible, AppUser appUser, Set<Grain> grains, Set<HopStore> hopStore, Set<Yeast> yeast) {
        this.style = style;
        this.name = name;
        this.efficiency = efficiency;
        this.volume = volume;
        this.boilTime = boilTime;
        this.visible = visible;
        this.appUser = appUser;
        this.grains = grains;
        this.hopStore = hopStore;
        this.yeast = yeast;
    }

    public Long getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Long id_recipe) {
        this.id_recipe = id_recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<Grain> getGrains() {
        return grains;
    }

    public void setGrains(Set<Grain> grains) {
        this.grains = grains;
    }

    public Set<HopStore> getHopStore() {
        return hopStore;
    }

    public void setHopStore(Set<HopStore> hopStore) {
        this.hopStore = hopStore;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(Double boilTime) {
        this.boilTime = boilTime;
    }

    public Set<Yeast> getYeast() {
        return yeast;
    }

    public void setYeast(Set<Yeast> yeast) {
        this.yeast = yeast;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id_recipe=" + id_recipe +
                ", style='" + style + '\'' +
                ", name='" + name + '\'' +
                ", efficiency=" + efficiency +
                ", volume=" + volume +
                ", boilTime=" + boilTime +
                ", visible=" + visible +
                ", appUser=" + appUser +
                ", grains=" + grains +
                ", hopStore=" + hopStore +
                ", yeast=" + yeast +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (visible != recipe.visible) return false;
        if (id_recipe != null ? !id_recipe.equals(recipe.id_recipe) : recipe.id_recipe != null) return false;
        if (style != null ? !style.equals(recipe.style) : recipe.style != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (efficiency != null ? !efficiency.equals(recipe.efficiency) : recipe.efficiency != null) return false;
        if (volume != null ? !volume.equals(recipe.volume) : recipe.volume != null) return false;
        if (boilTime != null ? !boilTime.equals(recipe.boilTime) : recipe.boilTime != null) return false;
        if (appUser != null ? !appUser.equals(recipe.appUser) : recipe.appUser != null) return false;
        if (grains != null ? !grains.equals(recipe.grains) : recipe.grains != null) return false;
        if (hopStore != null ? !hopStore.equals(recipe.hopStore) : recipe.hopStore != null) return false;
        return yeast != null ? yeast.equals(recipe.yeast) : recipe.yeast == null;

    }

    @Override
    public int hashCode() {
        int result = id_recipe != null ? id_recipe.hashCode() : 0;
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (efficiency != null ? efficiency.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (boilTime != null ? boilTime.hashCode() : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (appUser != null ? appUser.hashCode() : 0);
        result = 31 * result + (grains != null ? grains.hashCode() : 0);
        result = 31 * result + (hopStore != null ? hopStore.hashCode() : 0);
        result = 31 * result + (yeast != null ? yeast.hashCode() : 0);
        return result;
    }
}
