package com.pi.ati.ort.back.classes;

public class ModelRequest {

    private String name;
    private Long modelId;
    private int projectId;
    private Byte[] file;
    private String path;

    public ModelRequest() {
    }

    public ModelRequest(String name, int projectId,Long modelId, Byte[] file, String path) {
        this.name = name;
        this.modelId = modelId;
        this.projectId = projectId;
        this.file = file;
        this.path = path;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public Long getModelId() {
        return modelId;
    }
    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public Byte[] getFile() {
        return file;
    }
    public void setFile(Byte[] file) {
        this.file = file;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ModelRequest{" +
                "name='" + name + '\'' +
                ", modelId='" + modelId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", file='" + file + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
