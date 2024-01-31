package com.pi.ati.ort.back.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    private Byte file;
    @NotNull
    private String path;

    public Model() {
    }

    public Model(Long id, String name, String username, Byte file, String path) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.file = file;
        this.path = path;
    }

    public Model(String name, String username, Byte file, String path) {
        this.name = name;
        this.username = username;
        this.file = file;
        this.path = path;
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
    public Byte getFile() {
        return file;
    }
    public void setFile(Byte file) {
        this.file = file;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
