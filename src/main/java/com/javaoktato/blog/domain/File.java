package com.javaoktato.blog.domain;

import javax.persistence.*;

@Entity
public class File {

    @Id
    private String name;

    private String contentType;

    private String etag;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private FilePayload payload;

    public File() {
    }

    public File(String name, String contentType, String etag, byte[] payload) {
        this.name = name;
        this.contentType = contentType;
        this.etag = etag;
        this.payload = new FilePayload(payload);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public FilePayload getPayload() {
        return payload;
    }

    public void setPayload(FilePayload payload) {
        this.payload = payload;
    }
}
