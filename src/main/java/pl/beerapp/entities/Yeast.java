package pl.beerapp.entities;

import pl.beerapp.entities.enumeration.YeastPacking;
import pl.beerapp.entities.enumeration.YeastType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "yeasts")
public class Yeast implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lab;

    @Column(nullable = false)
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YeastType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YeastPacking packaging;

    @Column(nullable = false)
    private Double attenuation;

    public Yeast() {
    }

    public Yeast(String name, String lab, String symbol, YeastType type, YeastPacking packaging, Double attenuation) {
        this.name = name;
        this.lab = lab;
        this.symbol = symbol;
        this.type = type;
        this.packaging = packaging;
        this.attenuation = attenuation;
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

    public YeastType getType() {
        return type;
    }

    public void setType(YeastType type) {
        this.type = type;
    }

    public YeastPacking getPackaging() {
        return packaging;
    }

    public void setPackaging(YeastPacking packaging) {
        this.packaging = packaging;
    }

    public Double getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Double attenuation) {
        this.attenuation = attenuation;
    }

}
