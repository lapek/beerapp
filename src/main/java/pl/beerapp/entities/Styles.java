package pl.beerapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "styles")
public class Styles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_style", nullable = false)
    private Long id_style;

    @Column(name = "name", nullable = false)
    private String name;

    public Styles() {
    }

    public Styles(String name) {
        this.name = name;
    }

    public Long getId_style() {
        return id_style;
    }

    public void setId_style(Long id_style) {
        this.id_style = id_style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Styles styles = (Styles) o;

        if (id_style != null ? !id_style.equals(styles.id_style) : styles.id_style != null) return false;
        return name != null ? name.equals(styles.name) : styles.name == null;

    }

    @Override
    public int hashCode() {
        int result = id_style != null ? id_style.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Styles{" +
                "id_style=" + id_style +
                ", name='" + name + '\'' +
                '}';
    }
}
