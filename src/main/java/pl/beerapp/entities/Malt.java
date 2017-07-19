package pl.beerapp.entities;

import pl.beerapp.entities.enumeration.MaltType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "malts")
public class Malt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_malt")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "malt_type", nullable = false)
    private MaltType maltType;

    @Column(name = "colorEBC", nullable = false)
    private Double colorEBC;

    @Column(nullable = false)
    private Double potential;

    @Column(nullable = false)
    private Double max;

    public Malt() {
    }

    public Malt(String name, String country, MaltType maltType, Double colorEBC, Double potential, Double max) {
        this.name = name;
        this.country = country;
        this.maltType = maltType;
        this.colorEBC = colorEBC;
        this.potential = potential;
        this.max = max;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MaltType getMaltType() {
        return maltType;
    }

    public void setMaltType(MaltType maltType) {
        this.maltType = maltType;
    }

    public double getColorEBC() {
        return colorEBC;
    }

    public void setColorEBC(double colorEBC) {
        this.colorEBC = colorEBC;
    }

    public double getPotential() {
        return potential;
    }

    public void setPotential(double potential) {
        this.potential = potential;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Malt malts = (Malt) o;

        if (id != null ? !id.equals(malts.id) : malts.id != null) return false;
        if (name != null ? !name.equals(malts.name) : malts.name != null) return false;
        if (country != null ? !country.equals(malts.country) : malts.country != null) return false;
        if (maltType != malts.maltType) return false;
        if (colorEBC != null ? !colorEBC.equals(malts.colorEBC) : malts.colorEBC != null) return false;
        if (potential != null ? !potential.equals(malts.potential) : malts.potential != null) return false;
        return max != null ? max.equals(malts.max) : malts.max == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (maltType != null ? maltType.hashCode() : 0);
        result = 31 * result + (colorEBC != null ? colorEBC.hashCode() : 0);
        result = 31 * result + (potential != null ? potential.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Malt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", maltType=" + maltType +
                ", colorEBC=" + colorEBC +
                ", potential=" + potential +
                ", max=" + max +
                '}';
    }
}
