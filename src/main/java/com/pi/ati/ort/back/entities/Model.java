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
    private Long projectId;
    @NotNull
    private String name;
    @NotNull
    private Byte file;
    @NotNull
    private Long size;

    public Model() {
    }

    public Model(Long id, String name, Long projectId, Byte file, Long size) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.file = file;
        this.size = size;
    }

    public Model(String name, Long projectId, Byte file, Long size) {
        this.name = name;
        this.projectId = projectId;
        this.file = file;
        this.size = size;
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
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public Byte getFile() {
        return file;
    }
    public void setFile(Byte file) {
        this.file = file;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(String path) {
        this.size = size;
    }
}
