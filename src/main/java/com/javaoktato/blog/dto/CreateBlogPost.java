package com.javaoktato.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateBlogPost {

    @NotNull(message = "Title is mandatory")
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 5, max = 36, message = "Title must be between 5 and 20 characters")
    private String title;

    @NotNull(message = "Content is mandatory")
    @NotBlank(message = "Content cannot be blank")
    @Size(min = 20, message = "Content must have at least 20 characters")
    private String content;

    @NotNull(message = "Author email is mandatory")
    @Email(message = "Author email must be a valid email")
    private String authorEmail;

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

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
}
