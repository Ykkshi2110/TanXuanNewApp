package com.peter.tanxuannewapp.domain.resposne;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ResCreateUserDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private Instant createdAt;
    private ResRoleDTO role;
}
