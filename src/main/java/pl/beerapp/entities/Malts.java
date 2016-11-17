package pl.beerapp.entities;

import pl.beerapp.entities.enumeration.MaltType;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "malts")
public class Malts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_malt", nullable = false)
    private Long id_malt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "malt_type", nullable = false)
    private MaltType maltType;

    @Column(name = "colorEBC", nullable = false)
    private Double colorEBC;

    @Column(name = "potential", nullable = false)
    private Double potential;

    @Column(name = "max", nullable = false)
    private Double max;

    public Malts() {
    }

    public Malts(String name, String country, MaltType maltType, Double colorEBC, Double potential, Double max) {
        this.name = name;
        this.country = country;
        this.maltType = maltType;
        this.colorEBC = colorEBC;
        this.potential = potential;
        this.max = max;
    }

    public Long getId_malt() {
        return id_malt;
    }

    public void setId_malt(Long id_malt) {
        this.id_malt = id_malt;
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

        Malts malts = (Malts) o;

        if (id_malt != null ? !id_malt.equals(malts.id_malt) : malts.id_malt != null) return false;
        if (name != null ? !name.equals(malts.name) : malts.name != null) return false;
        if (country != null ? !country.equals(malts.country) : malts.country != null) return false;
        if (maltType != malts.maltType) return false;
        if (colorEBC != null ? !colorEBC.equals(malts.colorEBC) : malts.colorEBC != null) return false;
        if (potential != null ? !potential.equals(malts.potential) : malts.potential != null) return false;
        return max != null ? max.equals(malts.max) : malts.max == null;

    }

    @Override
    public int hashCode() {
        int result = id_malt != null ? id_malt.hashCode() : 0;
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
        return "Malts{" +
                "id_malt=" + id_malt +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", maltType=" + maltType +
                ", colorEBC=" + colorEBC +
                ", potential=" + potential +
                ", max=" + max +
                '}';
    }
}
