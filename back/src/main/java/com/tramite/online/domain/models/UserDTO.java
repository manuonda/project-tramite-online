package com.tramite.online.domain.models;


public class UserDTO {

    private Long id;
    private String userName;
    private String provider;
    private String email;

    
    public UserDTO() {
    }
    public UserDTO(Long id, String userName, String provider, String email) {
        this.id = id;
        this.userName = userName;
        this.provider = provider;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    

}
