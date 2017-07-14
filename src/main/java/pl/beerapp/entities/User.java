package pl.beerapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    //TODO add user roles
//    @Enumerated(EnumType.STRING)
//    @Column(name = "user_role", nullable = false)
//    private UserRole userRole;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    //@OneToMany(mappedBy = "user")
    //private List<Recipe> recipes;

    public User() {
    }

    public User(String username, String password, String email) {//, List<Recipe> recipes) {
        this.username = username;
        this.password = password;
        this.email = email;
        //this.recipes = recipes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Recipe> getRecipes() {
//        return recipes;
//    }

//    public void setRecipes(List<Recipe> recipes) {
//        this.recipes = recipes;
//    }
}
