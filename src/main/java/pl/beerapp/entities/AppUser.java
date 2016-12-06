package pl.beerapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany
    @JoinTable(
            name = "UserRecipes",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_recipe")
    )
    private Set<Recipe> recipes = new HashSet<>();

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AppUser(String login, String email, String password, Boolean enabled) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public AppUser(String login, String email, String password, Boolean enabled, Set<Recipe> recipes) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.recipes = recipes;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public static synchronized String hashPassword(String pass) {
        return passwordEncoder.encode(pass);
    }

    public static synchronized boolean doesPasswordMatch(String rawPass, String encodedPass) {
        return passwordEncoder.matches(rawPass, encodedPass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        if (id_user != null ? !id_user.equals(appUser.id_user) : appUser.id_user != null) return false;
        if (login != null ? !login.equals(appUser.login) : appUser.login != null) return false;
        if (email != null ? !email.equals(appUser.email) : appUser.email != null) return false;
        if (password != null ? !password.equals(appUser.password) : appUser.password != null) return false;
        if (enabled != null ? !enabled.equals(appUser.enabled) : appUser.enabled != null) return false;
        return recipes != null ? recipes.equals(appUser.recipes) : appUser.recipes == null;
    }

    @Override
    public int hashCode() {
        int result = id_user != null ? id_user.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (recipes != null ? recipes.hashCode() : 0);
        return result;
    }
}