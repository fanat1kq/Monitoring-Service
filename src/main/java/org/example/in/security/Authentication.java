package org.example.in.security;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    private String login;
    private boolean isAuth;
    private String message;
    public boolean isAuth() {
        return isAuth;
    }

}
