package com.udacity.jwdnd.course1.cloudstorage.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Credential {

    private Integer credentialId;
    private String key;
    private Integer userId;
    private String unscrambledPassword;

    @NotNull
    @Size(max=100)
    private String url;

    @NotNull
    @Size(max=30)
    private String username;

    @NotNull
    private String password;


    public Credential(Integer credentialId, String url, String username, String key, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public String getUnscrambledPassword() {
        return unscrambledPassword;
    }

    public void setUnscrambledPassword(String unscrambledPassword) {
        this.unscrambledPassword = unscrambledPassword;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
