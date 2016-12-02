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

    @Column(name = "batch_size", nullable = false)
    private Double batchSize;

    @Column(name = "boil_size", nullable = false)
    private Double boilSize;

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
    private Set<HopStore> hopStores = new HashSet<>();

    @OneToMany
    @JoinTable(
            name = "RecipeMashing",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_mashing")
    )
    private Set<Mashing> mashings = new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ferment_fk")
    private Fermentation fermentation;

    public Recipe() {
    }

    public Recipe(String style, String name, Double efficiency, Double batchSize, Double boilSize, Double boilTime, boolean visible, AppUser appUser, Set<Grain> grains, Set<HopStore> hopStores, Set<Mashing> mashings, Fermentation fermentation) {
        this.style = style;
        this.name = name;
        this.efficiency = efficiency;
        this.batchSize = batchSize;
        this.boilSize = boilSize;
        this.boilTime = boilTime;
        this.visible = visible;
        this.appUser = appUser;
        this.grains = grains;
        this.hopStores = hopStores;
        this.mashings = mashings;
        this.fermentation = fermentation;
    }

    public Long getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Long id_recipe) {
        this.id_recipe = id_recipe;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    public Double getBoilSize() {
        return boilSize;
    }

    public void setBoilSize(Double boilSize) {
        this.boilSize = boilSize;
    }

    public Double getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(Double boilTime) {
        this.boilTime = boilTime;
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

    public Set<HopStore> getHopStores() {
        return hopStores;
    }

    public void setHopStores(Set<HopStore> hopStores) {
        this.hopStores = hopStores;
    }

    public Set<Mashing> getMashings() {
        return mashings;
    }

    public void setMashings(Set<Mashing> mashings) {
        this.mashings = mashings;
    }

    public Fermentation getFermentation() {
        return fermentation;
    }

    public void setFermentation(Fermentation fermentation) {
        this.fermentation = fermentation;
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
        if (batchSize != null ? !batchSize.equals(recipe.batchSize) : recipe.batchSize != null) return false;
        if (boilSize != null ? !boilSize.equals(recipe.boilSize) : recipe.boilSize != null) return false;
        if (boilTime != null ? !boilTime.equals(recipe.boilTime) : recipe.boilTime != null) return false;
        if (appUser != null ? !appUser.equals(recipe.appUser) : recipe.appUser != null) return false;
        if (grains != null ? !grains.equals(recipe.grains) : recipe.grains != null) return false;
        if (hopStores != null ? !hopStores.equals(recipe.hopStores) : recipe.hopStores != null) return false;
        if (mashings != null ? !mashings.equals(recipe.mashings) : recipe.mashings != null) return false;
        return fermentation != null ? fermentation.equals(recipe.fermentation) : recipe.fermentation == null;
    }

    @Override
    public int hashCode() {
        int result = id_recipe != null ? id_recipe.hashCode() : 0;
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (efficiency != null ? efficiency.hashCode() : 0);
        result = 31 * result + (batchSize != null ? batchSize.hashCode() : 0);
        result = 31 * result + (boilSize != null ? boilSize.hashCode() : 0);
        result = 31 * result + (boilTime != null ? boilTime.hashCode() : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (appUser != null ? appUser.hashCode() : 0);
        result = 31 * result + (grains != null ? grains.hashCode() : 0);
        result = 31 * result + (hopStores != null ? hopStores.hashCode() : 0);
        result = 31 * result + (mashings != null ? mashings.hashCode() : 0);
        result = 31 * result + (fermentation != null ? fermentation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id_recipe=" + id_recipe +
                ", style='" + style + '\'' +
                ", name='" + name + '\'' +
                ", efficiency=" + efficiency +
                ", batchSize=" + batchSize +
                ", boilSize=" + boilSize +
                ", boilTime=" + boilTime +
                ", visible=" + visible +
                ", appUser=" + appUser +
                ", grains=" + grains +
                ", hopStores=" + hopStores +
                ", mashings=" + mashings +
                ", fermentation=" + fermentation +
                '}';
    }
}