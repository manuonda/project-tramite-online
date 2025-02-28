package com.tramite.online.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String userName;
    private String provider;
    private String email;

}
