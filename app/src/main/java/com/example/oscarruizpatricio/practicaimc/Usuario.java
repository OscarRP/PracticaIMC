package com.example.oscarruizpatricio.practicaimc;

/**
 * Created by oscarruizpatricio on 22/1/17.
 */

public class Usuario {

    private int id;
    private String user;
    private String password;

    public Usuario(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
