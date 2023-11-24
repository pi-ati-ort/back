package com.pi.ati.ort.back.classes;

public class TestRequest {

    private String adress;
    private String username;
    private String password;

    public TestRequest() {
    }

    public TestRequest(String adress, String username, String password) {
        this.adress = adress;
        this.username = username;
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
