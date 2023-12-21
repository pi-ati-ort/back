package com.pi.ati.ort.back.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long poid;

    @NotNull
    private String name;

    @NotNull
    private String schema;

    @NotNull String username;

    public Project() {
    }

    public Project(Long id, Long poid, String name, String schema, String username) {
        this.id = id;
        this.poid = poid;
        this.name = name;
        this.schema = schema;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public Long getPoid() {
        return poid;
    }
    public String getName() {
        return name;
    }
    public String getSchema() {
        return schema;
    }
    public void setPoid(Long poid) {
        this.poid = poid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSchema(String schema) {
        this.schema = schema;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}