package kg.attractor.movie_review_java23.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    private String resetPasswordToken;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
    private Collection<Role> roles;

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }


}
