package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "app_user")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long id_user;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @OneToMany
    @JoinTable(
            name = "UserRecipes",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_recipe")
    )
    private Set<Recipe> recipes = new HashSet<>();

    public AppUser() {
    }

    public AppUser(String username, String email, String password, Set<Recipe> recipes) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.recipes = recipes;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        if (id_user != null ? !id_user.equals(appUser.id_user) : appUser.id_user != null) return false;
        if (username != null ? !username.equals(appUser.username) : appUser.username != null) return false;
        if (email != null ? !email.equals(appUser.email) : appUser.email != null) return false;
        if (password != null ? !password.equals(appUser.password) : appUser.password != null) return false;
        return recipes != null ? recipes.equals(appUser.recipes) : appUser.recipes == null;

    }

    @Override
    public int hashCode() {
        int result = id_user != null ? id_user.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (recipes != null ? recipes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}