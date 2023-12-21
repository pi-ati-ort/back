package com.pi.ati.ort.back.classes;

public class ProjectRequest {

    private String name;
    private String schema;
    private String username;

    public ProjectRequest() {
    }

    public ProjectRequest(String name, String schema, String username) {
        this.name = name;
        this.schema = schema;
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public String getSchema() {
        return schema;
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
