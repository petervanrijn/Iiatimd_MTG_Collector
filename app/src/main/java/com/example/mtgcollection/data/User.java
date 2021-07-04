package com.example.mtgcollection.data;

public class User {
    private final int id;
    private final String username;
    private final String email;
    private final String name;
    private final String token;

    public User(int id, String name, String username, String email, String token) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

}
