package com.pi.ati.ort.back.classes;

public class ProjectRequest {
    private String name;
    private String description;
    private String schema;
    private String username;

    public ProjectRequest() {
    }

    public ProjectRequest(String name, String description, String schema, String username) {
        this.name = name;
        this.description = description;
        this.schema = schema;
        this.username = username;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSchema() {
        return schema;
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

    @Override
    public String toString() {
        return "ProjectRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", schema='" + schema + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
