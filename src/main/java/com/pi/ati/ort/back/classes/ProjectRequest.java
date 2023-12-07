package com.pi.ati.ort.back.classes;

public class ProjectRequest {

    private String name;
    private String schema;

    public ProjectRequest() {
    }

    public ProjectRequest(String name, String schema) {
        this.name = name;
        this.schema = schema;
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


}
