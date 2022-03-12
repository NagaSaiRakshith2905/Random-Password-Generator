package com.example.randompasswordgenerator.model;

public class Password {
    public String password;
    public String length;

    public Password(String password, String length) {
        this.password = password;
        this.length = length;
    }

    public Password() {
    }

    public String getPassword() {
        return password;
    }

    public String getLength() {
        return length;
    }
}
