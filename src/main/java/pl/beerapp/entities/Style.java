package pl.beerapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "styles")
public class Style implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_style")
    private Long idStyle;

    @Column(nullable = false)
    private String name;

    //TODO add default color, og etc

    public Style() {
    }

    public Style(String name) {
        this.name = name;
    }

    public Long getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(Long idStyle) {
        this.idStyle = idStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
