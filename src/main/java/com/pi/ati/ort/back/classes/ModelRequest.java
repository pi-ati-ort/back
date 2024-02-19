package com.pi.ati.ort.back.classes;

public class ModelRequest {

    private String filename;
    private Long projectId;
    private Byte[] file;
    private Long bimId;
    private Long size;

    public ModelRequest() {
    }

    public ModelRequest(String filename, Long projectId, Byte[] file, Long bimId, Long size) {
        this.filename = filename;
        this.projectId = projectId;
        this.file = file;
        this.bimId = bimId;
        this.size = size;
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
