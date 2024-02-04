package org.example.model;

import org.example.enumerates.Role;

import java.util.Objects;
/**
 * Created by fanat1kq on 04/02/2024.
 * This class is responsible for autorizations of users
 */
public class User {
    public String name;
    public String password;
    public Role role;
    /**
     * Creates a new User object.
     * @param name name of the user
     * @param password passwird of the user
     *@param role role us user
     */
    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }



    public User() {

    }
    /**
     *
     * @return the name of the user.
     */
    public String getName() {
        return name;
    }
    /**
     * set the name.
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     *
     * @return the password of the user.
     */

    public String getPassword() {
        return password;
    }
    /**
     * set the password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
