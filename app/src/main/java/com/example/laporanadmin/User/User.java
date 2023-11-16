package com.example.laporanadmin.User;

public class User {

    private String nama, email, password, nohp;

    public User() {

    }

    public User(String nama, String email, String password, String nohp) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.nohp = nohp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}

