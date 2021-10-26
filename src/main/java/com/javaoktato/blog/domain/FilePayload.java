package com.javaoktato.blog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FilePayload {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private byte[] data;

    public FilePayload() {
    }

    public FilePayload(byte[] payload) {
        this.data = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
