package com.example.mtgcollection.data;

public class User {
    private final int id;
    private final String username;
    private final String email;
    private final String token;

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
