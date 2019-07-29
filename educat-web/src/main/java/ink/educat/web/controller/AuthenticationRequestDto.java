package ink.educat.web.controller;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
