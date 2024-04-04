package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

/**
 * The player entity
 */
@Entity
@Table(name = "users", schema = "app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /*
     * The unique id player entity
     * */
    @Id
    @SequenceGenerator(name = "player_generator", sequenceName = "user_id_seq", allocationSize = 1, schema = "app")
    @GeneratedValue(generator = "player_generator", strategy = GenerationType.SEQUENCE)
    private long id;

    /*
     * The unique login player entity
     * */
    @NotNull
    @Column(name="username", unique=true)
    private String login;

    @NotNull
    private String password;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Role role;

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }
}

