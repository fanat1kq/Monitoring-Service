package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
/**
 * Created by fanat1kq on 04/02/2024.
 * This class is responsible for autorizations of users
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String password;
    private Role role;


}
