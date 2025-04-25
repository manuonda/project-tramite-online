package com.tramite.online.config.security.model;

import com.nimbusds.oauth2.sdk.assertions.AssertionDetails;

import jakarta.validation.constraints.AssertFalse;

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

     
    public UserInfo(String email, TypeProvider provider, String providerId) {
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }


    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
   
    public String getEmail() {
        return email;
    }
   
    public String getPicture() {
        return picture;
    }
   
    public TypeProvider getProvider() {
        return provider;
    }
    
    public String getProviderId() {
        return providerId;
    }
   
    

    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }


    public void setProvider(TypeProvider provider) {
        this.provider = provider;
    }


    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }



    // patter builder
    public static class Builder{
    
        private String id;
        private String name;
        private String email;
        private String picture;
        private TypeProvider provider;
        private String providerId;

    
    
    public Builder(String email, TypeProvider typeProvider, String providerId){
        if (email == null || provider == null) {
            throw new IllegalArgumentException("email and provider cannot be null");
        }
        this.email = email;
        this.provider = typeProvider;
        this.providerId = providerId;
    }

    public Builder(){

    }

    public Builder email(String email) {
        this.email = email;
        return this;
    }

    public Builder provider(TypeProvider provider) {
        this.provider = provider;
        return this;
    }

    public Builder providerId(String providerId) {
        this.providerId = providerId;
        return this;
    }


    public Builder id(String id){
        this.id = id;
        return this;
    }

    public Builder name(String name) {
        this.name = name;
        return this;
    }

    public Builder picture(String picture) {
        this.picture = picture;
        return this;
    }

    public UserInfo build() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(this.id);
        userInfo.setName(this.name);
        userInfo.setEmail(this.email);
        userInfo.setPicture(this.picture);
        userInfo.setProvider(this.provider);
        userInfo.setProviderId(this.providerId);
        return userInfo;
    }
   
  }
}
