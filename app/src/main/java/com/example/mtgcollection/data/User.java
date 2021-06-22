package com.example.mtgcollection.data;

public class User {
    private int id;
    private String username, email ,token;

    public User(int id, String username, String email, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

}
