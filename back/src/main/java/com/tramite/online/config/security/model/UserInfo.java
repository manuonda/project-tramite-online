package com.tramite.online.config.security.model;

/**
 * This class represents the user information extracted from an external provider.
 * It includes details such as the user's ID, name, email, profile picture, and the provider's name.
 */
public class UserInfo {

    private String id;
    private String name;
    private String email;
    private String picture;
    private TypeProvider provider;
    private String providerId;

    
    public UserInfo() {
    }

     
    public UserInfo(String id, String name, String email, String picture, TypeProvider provider, String providerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.provider = provider;
        this.providerId = providerId;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public TypeProvider getProvider() {
        return provider;
    }
    public void setProvider(TypeProvider provider) {
        this.provider = provider;
    }
    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }




}
