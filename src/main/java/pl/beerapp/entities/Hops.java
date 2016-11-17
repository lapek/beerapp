package pl.beerapp.entities;

import pl.beerapp.entities.enumeration.HopType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table
@Entity(name = "hops")
public class Hops implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHop", nullable = false)
    private Long idHop;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "hop_type", nullable = false)
    private HopType hopType;

    @Column(name = "alpha", nullable = false)
    private Double alpha;


    public Hops() {
    }

    public Hops(String name, String country, HopType hopType, Double alpha) {
        this.name = name;
        this.country = country;
        this.hopType = hopType;
        this.alpha = alpha;
    }

    public Long getIdHop() {
        return idHop;
    }

    public void setIdHop(Long idHop) {
        this.idHop = idHop;
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

    public HopType getHopType() {
        return hopType;
    }

    public void setHopType(HopType hopType) {
        this.hopType = hopType;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hops hops = (Hops) o;

        if (idHop != null ? !idHop.equals(hops.idHop) : hops.idHop != null) return false;
        if (name != null ? !name.equals(hops.name) : hops.name != null) return false;
        if (country != null ? !country.equals(hops.country) : hops.country != null) return false;
        if (hopType != hops.hopType) return false;
        return alpha != null ? alpha.equals(hops.alpha) : hops.alpha == null;

    }

    @Override
    public int hashCode() {
        int result = idHop != null ? idHop.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (hopType != null ? hopType.hashCode() : 0);
        result = 31 * result + (alpha != null ? alpha.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hops{" +
                "idHop=" + idHop +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", hopType=" + hopType +
                ", alpha=" + alpha +
                '}';
    }
}
