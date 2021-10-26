package com.javaoktato.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BlogUser {

    @Id
    private String email;
    private String name;
    private String password;

    private boolean active;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<BlogPost> posts;

    public BlogUser() {
    }

    public BlogUser(String email, String name, String password, boolean active) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<BlogPost> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogPost> posts) {
        this.posts = posts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogUser blogUser = (BlogUser) o;
        return Objects.equals(email, blogUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
