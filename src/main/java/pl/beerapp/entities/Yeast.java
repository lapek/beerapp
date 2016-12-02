package pl.beerapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "yeasts")
public class Yeast implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_yeast", nullable = false)
    private Long id_yeast;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lab", nullable = false)
    private String lab;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "type")
    private String type;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "attenuation", nullable = false)
    private Double attenuation;

    public Yeast() {
    }

    public Yeast(String name, String lab, String symbol, String type, String packaging, Double attenuation) {
        this.name = name;
        this.lab = lab;
        this.symbol = symbol;
        this.type = type;
        this.packaging = packaging;
        this.attenuation = attenuation;
    }

    public Long getId_yeast() {
        return id_yeast;
    }

    public void setId_yeast(Long id_yeast) {
        this.id_yeast = id_yeast;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public Double getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Double attenuation) {
        this.attenuation = attenuation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Yeast yeast = (Yeast) o;

        if (id_yeast != null ? !id_yeast.equals(yeast.id_yeast) : yeast.id_yeast != null) return false;
        if (name != null ? !name.equals(yeast.name) : yeast.name != null) return false;
        if (lab != null ? !lab.equals(yeast.lab) : yeast.lab != null) return false;
        if (symbol != null ? !symbol.equals(yeast.symbol) : yeast.symbol != null) return false;
        if (type != null ? !type.equals(yeast.type) : yeast.type != null) return false;
        if (packaging != null ? !packaging.equals(yeast.packaging) : yeast.packaging != null) return false;
        return attenuation != null ? attenuation.equals(yeast.attenuation) : yeast.attenuation == null;
    }

    @Override
    public int hashCode() {
        int result = id_yeast != null ? id_yeast.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lab != null ? lab.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (packaging != null ? packaging.hashCode() : 0);
        result = 31 * result + (attenuation != null ? attenuation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Yeast{" +
                "id_yeast=" + id_yeast +
                ", name='" + name + '\'' +
                ", lab='" + lab + '\'' +
                ", symbol='" + symbol + '\'' +
                ", type='" + type + '\'' +
                ", packaging='" + packaging + '\'' +
                ", attenuation=" + attenuation +
                '}';
    }
}
