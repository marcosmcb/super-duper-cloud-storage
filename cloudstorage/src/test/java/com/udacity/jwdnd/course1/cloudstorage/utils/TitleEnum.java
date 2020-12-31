package com.udacity.jwdnd.course1.cloudstorage.utils;

public enum TitleEnum {
    LOGIN("Login"),
    HOME("Home"),
    RESULT("Result");

    private final String value;

    TitleEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
