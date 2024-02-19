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
    private String username;
    @NotNull
    private Long projectId;
    private Long bimId;
    @NotNull
    private String filename;
    private Byte file;
    @NotNull
    private Long size;

    public Model() {
    }

    public Model(Long id, String filename, Long projectId, Long bimId, Byte file, Long size, String username) {
        this.id = id;
        this.username = username;
        this.projectId = projectId;
        this.bimId = bimId;
        this.filename = filename;
        this.file = file;
        this.size = size;
    }

    public Model(String filename, Long projectId, Long bimId, Byte file, Long size, String username) {
        this.username = username;
        this.filename = filename;
        this.projectId = projectId;
        this.bimId = bimId;
        this.file = file;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getBimId() {
        return bimId;
    }

    public void setBimId(Long bimId) {
        this.bimId = bimId;
    }
}
