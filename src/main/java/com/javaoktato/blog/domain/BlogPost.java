package com.javaoktato.blog.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Lob
    private String content;

    private LocalDateTime creationDate;

    @ManyToOne
    private BlogUser author;

    public BlogPost() {
    }

    public BlogPost(String title, String content, LocalDateTime creationDate, BlogUser author) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BlogUser getAuthor() {
        return author;
    }

    public void setAuthor(BlogUser author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("[ %d, \"%s\", %s, %s]", id, title, creationDate, author.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(id, blogPost.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}