package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonAutoDetect
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_recipe", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_style", nullable = false)
    private Style style;

    @Column(nullable = false)
    private Double efficiency;

    @Column(nullable = false)
    private Double batchSize;

    @Column(nullable = false)
    private Double boilSize;

    @Column(nullable = false)
    private Double boilTime;

    @Column(nullable = false)
    private Boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_grain",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_grain")
    )
    @JsonManagedReference
    private List<Grain> grains;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_hop_store",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_store")
    )
    @JsonManagedReference
    private List<HopStore> hopStores;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_mashing",
            joinColumns = @JoinColumn(name = "id_recipe", referencedColumnName = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_mashing", referencedColumnName = "id_mashing")
    )
    @JsonManagedReference
    private List<Mashing> mashings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ferment")
    @JsonManagedReference
    private Fermentation fermentation;

    public Recipe() {
    }

    public Recipe(Style style, String name, String author, Double efficiency, Double batchSize, Double boilSize, Double boilTime, boolean visible, User user, List<Grain> grains, List<HopStore> hopStores, List<Mashing> mashings, Fermentation fermentation) {
        this.style = style;
        this.name = name;
        this.author = author;
        this.efficiency = efficiency;
        this.batchSize = batchSize;
        this.boilSize = boilSize;
        this.boilTime = boilTime;
        this.visible = visible;
        this.user = user;
        this.grains = grains;
        this.hopStores = hopStores;
        this.mashings = mashings;
        this.fermentation = fermentation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Grain> getGrains() {
        return grains;
    }

    public void setGrains(List<Grain> grains) {
        this.grains = grains;
    }

    public List<HopStore> getHopStores() {
        return hopStores;
    }

    public void setHopStores(List<HopStore> hopStores) {
        this.hopStores = hopStores;
    }

    public List<Mashing> getMashings() {
        return mashings;
    }

    public void setMashings(List<Mashing> mashings) {
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
        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (style != null ? !style.equals(recipe.style) : recipe.style != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (author != null ? !author.equals(recipe.author) : recipe.author != null) return false;
        if (efficiency != null ? !efficiency.equals(recipe.efficiency) : recipe.efficiency != null) return false;
        if (batchSize != null ? !batchSize.equals(recipe.batchSize) : recipe.batchSize != null) return false;
        if (boilSize != null ? !boilSize.equals(recipe.boilSize) : recipe.boilSize != null) return false;
        if (boilTime != null ? !boilTime.equals(recipe.boilTime) : recipe.boilTime != null) return false;
        if (user != null ? !user.equals(recipe.user) : recipe.user != null) return false;
        if (grains != null ? !grains.equals(recipe.grains) : recipe.grains != null) return false;
        if (hopStores != null ? !hopStores.equals(recipe.hopStores) : recipe.hopStores != null) return false;
        if (mashings != null ? !mashings.equals(recipe.mashings) : recipe.mashings != null) return false;
        return fermentation != null ? fermentation.equals(recipe.fermentation) : recipe.fermentation == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (efficiency != null ? efficiency.hashCode() : 0);
        result = 31 * result + (batchSize != null ? batchSize.hashCode() : 0);
        result = 31 * result + (boilSize != null ? boilSize.hashCode() : 0);
        result = 31 * result + (boilTime != null ? boilTime.hashCode() : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (grains != null ? grains.hashCode() : 0);
        result = 31 * result + (hopStores != null ? hopStores.hashCode() : 0);
        result = 31 * result + (mashings != null ? mashings.hashCode() : 0);
        result = 31 * result + (fermentation != null ? fermentation.hashCode() : 0);
        return result;
    }

}
