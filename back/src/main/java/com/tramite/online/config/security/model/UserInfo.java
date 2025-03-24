package com.tramite.online.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class represents the user information extracted from an external provider.
 * It includes details such as the user's ID, name, email, profile picture, and the provider's name.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String id;
    private String name;
    private String email;
    private String picture;
    private TypeProvider provider;
    private String providerId;
}
