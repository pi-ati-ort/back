package com.pi.ati.ort.back.entities;

import com.pi.ati.ort.back.classes.ProjectRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @NotNull
    private String name;
    @NotNull
    private String schema;
    @NotNull
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long poid;
    @NotNull
    private Date created_at = new Date();

    public Project() {
    }

    public Project(String name, String schema, String username, Long poid) {
        this.name = name;
        this.schema = schema;
        this.username = username;
        this.poid = poid;

    }

//    public static Project fromRequest(ProjectRequest request) {
//        Project project = new Project();
//        System.out.println("ProjectRequest: " + request.toString());
//        project.setName(request.getName());
//        project.setSchema(request.getSchema());
//        project.setUsername(request.getUsername());
//        project.setPoid(121L);
//        return project;
//    }

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
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", schema='" + schema + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                ", poid=" + poid +
                ", created_at=" + created_at +
                '}';
    }
}