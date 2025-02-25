package com.peter.tanxuannewapp.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqLoginDTO {
    @NotBlank(message = "Username must not be blank!")
    private String username;

    @NotBlank(message = "Password must not be blank!")
    private String password;
}
