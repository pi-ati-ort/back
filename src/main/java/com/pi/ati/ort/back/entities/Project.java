package com.pi.ati.ort.back.entities;

import com.pi.ati.ort.back.classes.ProjectRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "projects")
public class Project {
    @NotNull
    private String name;
    private String description;
    @NotNull
    private String schema;
    @NotNull
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int poid;
    @NotNull
    private UUID uuid;
    @NotNull
    private Date created_at = new Date();
    @NotNull
    private String address;
    @NotNull
    private String padron;

    public Project() {
    }

    public Project(String name, String description, String schema, String username, int poid, UUID uuid, String address, String padron) {
        this.name = name;
        this.description = description;
        this.schema = schema;
        this.username = username;
        this.poid = poid;
        this.uuid = uuid;
        this.address = address;
        this.padron = padron;
    }

    public static Project fromRequest(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setSchema(request.getSchema());
        project.setUsername(request.getUsername());
        project.setAddress(request.getAddress());
        project.setPadron(request.getPadron());
        return project;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getSchema() {
        return schema;
    }
    public int getPoid() {
        return poid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSchema(String schema) {
        this.schema = schema;
    }
    public void setPoid(int poid) {
        this.poid = poid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPadron() {
        return padron;
    }
    public void setPadron(String padron) {
        this.padron = padron;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", schema='" + schema + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                ", poid=" + poid +
                ", uuid=" + uuid +
                ", created_at=" + created_at +
                ", address='" + address + '\'' +
                ", padron='" + padron + '\'' +
                '}';
    }
}