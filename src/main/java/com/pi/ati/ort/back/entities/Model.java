package com.pi.ati.ort.back.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    private Byte[] file;

    public Model() {
    }

    public Model(Long id, String name, String username, Byte[] file) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.file = file;
    }

    public Model(String name, String username, Byte[] file) {
        this.name = name;
        this.username = username;
        this.file = file;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Byte[] getFile() {
        return file;
    }
    public void setFile(Byte[] file) {
        this.file = file;
    }

}
