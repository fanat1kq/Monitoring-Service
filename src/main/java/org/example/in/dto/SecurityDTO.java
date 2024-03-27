package org.example.in.dto;

//для входа под пользователем(на сервлет с json)
public record SecurityDTO(String login, String password, String role) {

}
