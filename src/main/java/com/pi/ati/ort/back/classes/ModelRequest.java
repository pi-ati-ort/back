package com.pi.ati.ort.back.classes;

public class ModelRequest {

    private String filename;
    private Long projectId;
    private Byte[] file;
    private Long bimId;
    private Long size;
    private String username;

    public ModelRequest() {
    }

    public ModelRequest(String filename, Long projectId, Byte[] file, Long bimId, Long size, String username) {
        this.filename = filename;
        this.projectId = projectId;
        this.file = file;
        this.bimId = bimId;
        this.size = size;
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Byte[] getFile() {
        return file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public Long getBimId() {
        return bimId;
    }

    public void setBimId(Long bimId) {
        this.bimId = bimId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ModelRequest{" +
                "filename='" + filename + '\'' +
                ", projectId='" + projectId + '\'' +
                ", file='" + file + '\'' +
                ", bimId='" + bimId + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
