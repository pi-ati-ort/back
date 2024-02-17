package com.pi.ati.ort.back.classes;

public class ProjectRequest {
    private String name;
    private String description;
    private String schema;
    private String username;
    private String address;
    private String padron;


    public ProjectRequest() {
    }

    public ProjectRequest(String name, String description, String schema, String username, String address, String padron) {
        this.name = name;
        this.description = description;
        this.schema = schema;
        this.username = username;
        this.address = address;
        this.padron = padron;
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
        return "ProjectRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", schema='" + schema + '\'' +
                ", username='" + username + '\'' +
                ", adress='" + address + '\'' +
                ", padron='" + padron + '\'' +
                '}';
    }
}
